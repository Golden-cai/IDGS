package org.lyy.graduationproject.idgs.infrastructure.Response;

import org.lyy.graduationproject.idgs.infrastructure.VO.DepartmentVO;

import java.util.List;

/**
 * description: DepartmentResponse
 * date: 2023/4/23 1:42
 * author: caugi
 * version: 1.0
 */
public class DepartmentResponse extends DepartmentVO {

    private List<DepartmentVO> secondaryDepartmentList;

    public List<DepartmentVO> getSecondaryDepartment() {
        return secondaryDepartmentList;
    }

    public void setSecondaryDepartment(List<DepartmentVO> secondaryDepartmentList) {
        this.secondaryDepartmentList = secondaryDepartmentList;
    }
}
