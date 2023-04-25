package org.lyy.graduationproject.idgs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.WebResultEnum;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentItemPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.HospitalInfoPO;
import org.lyy.graduationproject.idgs.infrastructure.Response.DepartmentResponse;
import org.lyy.graduationproject.idgs.infrastructure.Response.HospitalInfoResponse;
import org.lyy.graduationproject.idgs.infrastructure.VO.DepartmentVO;
import org.lyy.graduationproject.idgs.mapper.DepartmentDao;
import org.lyy.graduationproject.idgs.mapper.DepartmentItemDao;
import org.lyy.graduationproject.idgs.mapper.HospitalDao;
import org.lyy.graduationproject.idgs.service.IHospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

/**
 * description: HospitalService
 * date: 2023/4/16 20:54
 * author: lyy
 * version: 1.0
 */
@Service
public class HospitalService implements IHospitalService {
    Logger logger = LoggerFactory.getLogger(HospitalService.class);

    private final Snowflake snowFlake = IdUtil.getSnowflake();

    @Autowired
    private HospitalDao hospitalDao;

    @Autowired
    private DepartmentItemDao departmentItemDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<HospitalInfoPO> queryAllHospital() throws ApplicationException {
        try {
            return hospitalDao.queryAllHospital();
        } catch (SQLException e) {
            logger.error("queryAllHospital: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }

    @Override
    public int addAllDepartmentForHospital(Long hospitalId) throws ApplicationException {
        if (hospitalId == null) {
            logger.error("addAllDepartmentForHospital: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            List<DepartmentPO> departmentPOS = new ArrayList<>();
            // 获取department_item_t中的所有科室
            List<DepartmentItemPO> departmentItemPOS = departmentItemDao.queryAllDepartmentItem();
            for (DepartmentItemPO departmentItemPO : departmentItemPOS) {
                DepartmentPO departmentPO = new DepartmentPO();
                departmentPO.setDepartmentId(snowFlake.nextId());
                departmentPO.setHospitalId(hospitalId);
                departmentPO.setDepartmentItemId(departmentItemPO.getDepartmentItemId());
                departmentPO.setDepartmentDescription(departmentItemPO.getDepartmentItemDescription());
                departmentPO.setDepartmentAddress("测试地址,随便填填");
                departmentPO.setCreateBy(-1L);
                departmentPO.setLastUpdateBy(-1L);
                departmentPOS.add(departmentPO);
            }
            return departmentDao.addDepartment(departmentPOS);
        } catch (SQLException e) {
            logger.error("addAllDepartmentForHospital: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }

    @Override
    public List<DepartmentResponse> queryAllAllDepartmentByHospital(Long hospitalId) throws ApplicationException {
        if (hospitalId == null) {
            logger.error("queryAllAllDepartmentByHospital: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            List<DepartmentResponse> result = new ArrayList<>();
            // 根据hospitalId查询所有的科室信息
            List<DepartmentVO> DepartmentVOS = departmentDao.queryDepartment(hospitalId);
            Map<Long, List<DepartmentVO>> secondaryMap = new HashMap<>();
            for (DepartmentVO departmentVO : DepartmentVOS) {
                // 判断科室类型: 二级科室放到父科室的map中
                if (Objects.equals(departmentVO.getDepartmentGrade(), "2")) {
                    if (!secondaryMap.containsKey(departmentVO.getDepartmentFatherId())) {
                        secondaryMap.put(departmentVO.getDepartmentFatherId(), new ArrayList<>());
                    }
                    secondaryMap.get(departmentVO.getDepartmentFatherId()).add(departmentVO);
                } else {
                    DepartmentResponse primaryDepartment = new DepartmentResponse();
                    BeanUtil.copyProperties(departmentVO, primaryDepartment);
                    result.add(primaryDepartment);
                }
            }
            result.forEach(item -> item.setSecondaryDepartment(secondaryMap.get(item.getDepartmentItemId())));
            return result;
        } catch (SQLException e) {
            logger.error("queryAllAllDepartmentByHospital: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        } catch (NullPointerException e) {
            logger.error("queryAllAllDepartmentByHospital: params is error", e);
            throw new ApplicationException(WebResultEnum.PARAM_ERROE);
        }
    }
}
