package org.lyy.graduationproject.idgs.service;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.request.DepartmentItemRequest;

/**
 * description: IDepartmentService
 * date: 2023/4/22 13:42
 * author: caugi
 * version: 1.0
 */
public interface IDepartmentService {

    /**
     * @Description: 添加科室item
     *
     * @param departmentItemList 科室List
     * @return 插入数量
     * @throws
     */
    int addDepartmentItem(DepartmentItemRequest departmentItemList) throws ApplicationException;
}
