package org.lyy.graduationproject.idgs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Result;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.WebResultEnum;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentItemPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorShiftsPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.DoctorShiftsRecordVO;
import org.lyy.graduationproject.idgs.infrastructure.VO.DoctorShiftsVO;
import org.lyy.graduationproject.idgs.infrastructure.VO.ShiftsRecordVO;
import org.lyy.graduationproject.idgs.infrastructure.request.DoctorShiftsRequest;
import org.lyy.graduationproject.idgs.mapper.DoctorInfoDao;
import org.lyy.graduationproject.idgs.mapper.DoctorShiftsDao;
import org.lyy.graduationproject.idgs.service.IShiftsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * description: ShiftsService
 * date: 2023/4/30 15:10
 * author: caugi
 * version: 1.0
 */

@Service
public class ShiftsService implements IShiftsService {
    Logger logger = LoggerFactory.getLogger(ShiftsService.class);

    private final Snowflake snowFlake = IdUtil.getSnowflake();

    @Autowired
    private DoctorInfoDao doctorInfoDao;

    @Autowired
    private DoctorShiftsDao doctorShiftsDao;

    @Override
    public List<DoctorShiftsRecordVO> queryShiftsByDepartmentId(Long departmentId) throws ApplicationException {
        if (departmentId == null) {
            logger.error("queryShiftsByDepartmentId: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            List<DoctorShiftsRecordVO> result = new ArrayList<>();
            // 获取当前时间
            Calendar now = Calendar.getInstance();
            // 获取今天星期几 Calendar 0代表星期日、1代表星期1、2代表星期2
            int nowDay = now.get(Calendar.DAY_OF_WEEK) - 1;
            // 查询科室下所有医生
            List<DoctorInfoPO> doctorInfoS = doctorInfoDao.queryAllDoctorByDepartmentId(departmentId);
            // 查询医生的班次
            for (DoctorInfoPO doctor : doctorInfoS) {
                List<DoctorShiftsPO> doctorShiftsList = doctorShiftsDao.queryDoctorShifts(doctor.getDoctorId(), "1");
                // 医生没有排班信息
                if (CollectionUtil.isEmpty(doctorShiftsList)) {
                    continue;
                }
                // 根据班次id查询具体班次情况
                List<ShiftsRecordVO> shiftsRecordVOS = new ArrayList<>();
                for (DoctorShiftsPO doctorShiftsPO : doctorShiftsList) {
                    // 判断班次是否有效 班次结束时间 < 当前时间，更新班次无效
                    if (doctorShiftsPO.getEndTime().getTime() < now.getTime().getTime()) {
                        doctorShiftsDao.updateShiftsStatusByShiftId(doctorShiftsPO.getShiftsId(), "2");
                        continue;
                    }
                    // 计算目标时间
                    int shiftsDay = doctorShiftsPO.getShiftsDay();
                    Calendar targetCalendar = Calendar.getInstance();
                    // 时间清零,只保留日期
                    targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
                    targetCalendar.set(Calendar.MINUTE, 0);
                    targetCalendar.set(Calendar.SECOND, 0);
                    targetCalendar.set(Calendar.MILLISECOND, 0);
                    targetCalendar.add(Calendar.DATE, (shiftsDay - nowDay + 7) % 7);

                    ShiftsRecordVO shiftsRecordVO = doctorShiftsDao.queryDoctorShiftRecord(doctorShiftsPO.getShiftsId(), targetCalendar.getTime());
                    // 当前班次未生成时, 生成新的班次
                    if (shiftsRecordVO == null) {
                        shiftsRecordVO = new ShiftsRecordVO();
                        shiftsRecordVO.setShiftsRecordId(snowFlake.nextId());
                        shiftsRecordVO.setShiftsId(doctorShiftsPO.getShiftsId());
                        shiftsRecordVO.setShiftsTime(targetCalendar.getTime());
                        shiftsRecordVO.setShiftsRemainNumber(doctorShiftsPO.getShiftsTotalNumber());
                        shiftsRecordVO.setCreateBy(-1L);
                        shiftsRecordVO.setLastUpdateBy(-1L);
                        doctorShiftsDao.addShiftsRecord(shiftsRecordVO);
                    }
                    shiftsRecordVO.setDoctorId(doctorShiftsPO.getDoctorId());
                    shiftsRecordVO.setShiftsDay(doctorShiftsPO.getShiftsDay());
                    shiftsRecordVO.setShiftsTotalNumber(doctorShiftsPO.getShiftsTotalNumber());
                    shiftsRecordVO.setShiftsAmount(doctorShiftsPO.getShiftsAmount());
                    shiftsRecordVOS.add(shiftsRecordVO);
                }
                DoctorShiftsRecordVO doctorShiftsRecordVO = new DoctorShiftsRecordVO();
                doctorShiftsRecordVO.setDoctorInfo(doctor);
                doctorShiftsRecordVO.setShiftsRecordList(shiftsRecordVOS);
                result.add(doctorShiftsRecordVO);
            }
            return result;
        } catch (SQLException e) {
            logger.error("queryShiftsByDepartmentId: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }

    @Override
    public int addShifts(DoctorShiftsRequest DoctorShifts) throws ApplicationException {
        if (CollectionUtil.isEmpty(DoctorShifts.getShifts())) {
            logger.error("addShifts: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            Iterator it = DoctorShifts.getShifts().listIterator();
            while (it.hasNext()) {
                Object next = it.next();
                DoctorShiftsPO doctorShifts = (DoctorShiftsPO) next;
                if (checkData(doctorShifts)) {
                    doctorShiftsDao.updateShiftsStatusNotUse(doctorShifts.getDoctorId());
                    // 新增班次
                    doctorShifts.setShiftsId(snowFlake.nextId());
                    doctorShifts.setShiftsStatus("1");
                    doctorShifts.setCreateBy(-1L);
                    doctorShifts.setLastUpdateBy(-1L);
                } else {
                    it.remove();
                }
            }
            return doctorShiftsDao.addShifts(DoctorShifts.getShifts());
        } catch (SQLException e) {
            logger.error("addShifts: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }

    // 检查数据有效性
    private boolean checkData(DoctorShiftsPO doctorShifts) {
        if (doctorShifts == null || doctorShifts.getShiftsDay() == null ) {
            return false;
        }
        Integer shiftDay = doctorShifts.getShiftsDay();
        if (shiftDay < 1 || shiftDay > 7) {
            return false;
        }
        if (doctorShifts.getStartTime() == null) {
            doctorShifts.setStartTime(new Date());
        }
        // 默认时间长度为1年
        if (doctorShifts.getEndTime() == null) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
            logger.info(c.getTime().toString());
            doctorShifts.setEndTime(c.getTime());
        }
        return true;
    }
}
