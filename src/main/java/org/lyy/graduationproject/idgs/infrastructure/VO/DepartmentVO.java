package org.lyy.graduationproject.idgs.infrastructure.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentPO;

import java.util.List;

/**
 * description: DepartmentVO
 * date: 2023/4/22 22:32
 * author: caugi
 * version: 1.0
 */
public class DepartmentVO extends DepartmentPO {

    // 科室名
    private String departmentItemName;

    // 科室描述
    private String departmentItemDescription;

    // 科室级别
    private String departmentGrade;

    // 父科室id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long departmentFatherId;

    public Long getDepartmentFatherId() {
        return departmentFatherId;
    }

    public void setDepartmentFatherId(Long departmentFatherId) {
        this.departmentFatherId = departmentFatherId;
    }

    public String getDepartmentItemName() {
        return departmentItemName;
    }

    public void setDepartmentItemName(String departmentItemName) {
        this.departmentItemName = departmentItemName;
    }

    public String getDepartmentItemDescription() {
        return departmentItemDescription;
    }

    public void setDepartmentItemDescription(String departmentItemDescription) {
        this.departmentItemDescription = departmentItemDescription;
    }

    public String getDepartmentGrade() {
        return departmentGrade;
    }

    public void setDepartmentGrade(String departmentGrade) {
        this.departmentGrade = departmentGrade;
    }
}
