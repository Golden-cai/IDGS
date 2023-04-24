package org.lyy.graduationproject.idgs.infrastructure.request;

import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentItemPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.DepartmentItemVO;

import java.util.List;

/**
 * description: DepartmentItemRequest
 * date: 2023/4/22 22:43
 * author: caugi
 * version: 1.0
 */
public class DepartmentItemRequest {

    // 一级科室列表
    private List<DepartmentItemVO> primaryDepartmentItem;

    public List<DepartmentItemVO> getPrimaryDepartmentItem() {
        return primaryDepartmentItem;
    }

    public void setPrimaryDepartmentItem(List<DepartmentItemVO> primaryDepartmentItem) {
        this.primaryDepartmentItem = primaryDepartmentItem;
    }
}
