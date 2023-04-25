package org.lyy.graduationproject.idgs.infrastructure.request;

import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;

import java.util.List;

/**
 * description: DoctorInfoRequest
 * date: 2023/4/25 23:11
 * author: caugi
 * version: 1.0
 */
public class DoctorInfoRequest {

    private List<DoctorInfoPO> doctorList;

    public List<DoctorInfoPO> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<DoctorInfoPO> doctorList) {
        this.doctorList = doctorList;
    }
}
