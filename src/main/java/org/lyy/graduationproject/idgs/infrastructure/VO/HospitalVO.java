package org.lyy.graduationproject.idgs.infrastructure.VO;

import org.lyy.graduationproject.idgs.infrastructure.PO.HospitalInfoPO;

import java.util.List;

/**
 * description: HospitalVO 医院的详细信息
 * date: 2023/4/22 22:26
 * author: caugi
 * version: 1.0
 */
public class HospitalVO {

    // 医院信息
    private HospitalInfoPO hospitalInfo;

    // 一级科室列表
    private List<DepartmentVO> primaryDepartmentInfoList;

}
