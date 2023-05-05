package org.lyy.graduationproject.idgs.infrastructure.VO;

import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorShiftsPO;

import java.util.List;

/**
 * description: DoctorShiftsVO
 * date: 2023/4/30 15:04
 * author: caugi
 * version: 1.0
 */
public class DoctorShiftsVO {

    // 医生信息
    private DoctorInfoPO doctorInfo;

    // 医生排班
    private List<DoctorShiftsPO> doctorShiftsList;

    public DoctorInfoPO getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(DoctorInfoPO doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public List<DoctorShiftsPO> getDoctorShiftsList() {
        return doctorShiftsList;
    }

    public void setDoctorShiftsList(List<DoctorShiftsPO> doctorShiftsList) {
        this.doctorShiftsList = doctorShiftsList;
    }
}
