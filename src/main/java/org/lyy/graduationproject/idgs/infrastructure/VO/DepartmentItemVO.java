package org.lyy.graduationproject.idgs.infrastructure.VO;

import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentItemPO;

import java.util.List;

/**
 * description: DepartmentItemVO
 * date: 2023/4/22 22:46
 * author: caugi
 * version: 1.0
 */
public class DepartmentItemVO extends DepartmentItemPO {

    // 二级科目列表
    private List<DepartmentItemPO> secondaryDepartmentItem;

    public List<DepartmentItemPO> getSecondaryDepartmentItem() {
        return secondaryDepartmentItem;
    }

    public void setSecondaryDepartmentItem(List<DepartmentItemPO> secondaryDepartmentItem) {
        this.secondaryDepartmentItem = secondaryDepartmentItem;
    }
}
