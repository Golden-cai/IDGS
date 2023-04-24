package org.lyy.graduationproject.idgs.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.WebResultEnum;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentItemPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.DepartmentItemVO;
import org.lyy.graduationproject.idgs.infrastructure.request.DepartmentItemRequest;
import org.lyy.graduationproject.idgs.mapper.DepartmentItemDao;
import org.lyy.graduationproject.idgs.service.IDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * description: IDepartmentService
 * date: 2023/4/22 21:48
 * author: caugi
 * version: 1.0
 */
@Service
public class DepartmentService implements IDepartmentService {
    Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    private final Snowflake snowFlake = IdUtil.getSnowflake();

    @Autowired
    private DepartmentItemDao departmentItemDao;

    @Override
    public int addDepartmentItem(DepartmentItemRequest departmentItemList) throws ApplicationException {
        if (departmentItemList == null || departmentItemList.getPrimaryDepartmentItem() == null) {
            logger.error("queryAllHospital: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            List<DepartmentItemPO> departmentItemPOS = new ArrayList<>();
            for (DepartmentItemVO departmentItemVO : departmentItemList.getPrimaryDepartmentItem()) {
                DepartmentItemPO primaryDepartment = setPrimaryDepartmentInfo(departmentItemVO);
                departmentItemPOS.add(primaryDepartment);
                // 添加2级科室
                if (primaryDepartment.getDepartmentGrade() == "1") {
                    for (DepartmentItemPO departmentItemPO: departmentItemVO.getSecondaryDepartmentItem()) {
                        DepartmentItemPO secondaryDepartment = new DepartmentItemPO();
                        setSecondaryDepartmentInfo(primaryDepartment, departmentItemPO, secondaryDepartment);
                        departmentItemPOS.add(secondaryDepartment);
                    }
                }
            }
            return departmentItemDao.addDepartmentItem(departmentItemPOS);
        } catch (SQLException e) {
            logger.error("queryAllHospital: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        } catch (NullPointerException e) {
            logger.error("queryAllHospital: params is error", e);
            throw new ApplicationException(WebResultEnum.PARAM_ERROE);
        }
    }

    private void setSecondaryDepartmentInfo(DepartmentItemPO primaryDepartment, DepartmentItemPO departmentItemPO, DepartmentItemPO secondaryDepartment) {
        secondaryDepartment.setDepartmentItemName(departmentItemPO.getDepartmentItemName());
        secondaryDepartment.setDepartmentItemDescription(departmentItemPO.getDepartmentItemDescription());
        secondaryDepartment.setDepartmentGrade("2");
        secondaryDepartment.setDepartmentFatherId(primaryDepartment.getDepartmentItemId());
        secondaryDepartment.setDepartmentItemId(snowFlake.nextId());
    }

    private DepartmentItemPO setPrimaryDepartmentInfo(DepartmentItemVO departmentItemVO) {
        DepartmentItemPO primaryDepartment = new DepartmentItemPO();
        primaryDepartment.setDepartmentItemName(departmentItemVO.getDepartmentItemName());
        primaryDepartment.setDepartmentItemDescription(departmentItemVO.getDepartmentItemDescription());
        // 没有二级科室时, 表示为功能科室
        if (CollectionUtil.isEmpty(departmentItemVO.getSecondaryDepartmentItem())) {
            primaryDepartment.setDepartmentGrade("3");
        } else {
            primaryDepartment.setDepartmentGrade("1");
        }
        primaryDepartment.setDepartmentItemId(snowFlake.nextId());
        return primaryDepartment;
    }
}
