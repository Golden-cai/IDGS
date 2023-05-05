package org.lyy.graduationproject.idgs.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.WebResultEnum;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;
import org.lyy.graduationproject.idgs.infrastructure.request.DoctorInfoRequest;
import org.lyy.graduationproject.idgs.mapper.DoctorInfoDao;
import org.lyy.graduationproject.idgs.service.IDoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * description: DoctorService
 * date: 2023/4/25 23:33
 * author: caugi
 * version: 1.0
 */
@Service
public class DoctorService implements IDoctorService {
    Logger logger = LoggerFactory.getLogger(DoctorService.class);

    private final Snowflake snowFlake = IdUtil.getSnowflake();

    @Autowired
    private DoctorInfoDao doctorInfoDao;

    @Override
    public int addDoctor(DoctorInfoRequest request) throws ApplicationException {
        if (request == null || request.getDoctorList() == null) {
            logger.error("addDoctor: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            List<DoctorInfoPO> newDoctorList = request.getDoctorList();
            for (DoctorInfoPO doctorInfo : newDoctorList) {
                doctorInfo.setDoctorId(snowFlake.nextId());
                doctorInfo.setCreateBy(-1L);
                doctorInfo.setLastUpdateBy(-1L);
            }
            return doctorInfoDao.addDoctorInfo(newDoctorList);
        } catch (SQLException e) {
            logger.error("addDoctor: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }

    @Override
    public DoctorInfoPO queryDoctorByDepartmentId(Long doctorId) throws ApplicationException {
        if (doctorId == null ) {
            logger.error("queryDoctorByDepartmentId: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            return doctorInfoDao.queryDoctorById(doctorId);
        } catch (SQLException e) {
            logger.error("queryDoctorByDepartmentId: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }
}
