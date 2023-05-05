package org.lyy.graduationproject.idgs.infrastructure.VO;

import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;

import java.util.List;

/**
 * description: DoctorShiftsRecordVO 实际班次
 * date: 2023/5/4 1:07
 * author: caugi
 * version: 1.0
 */
public class DoctorShiftsRecordVO {

    // 医生信息
    private DoctorInfoPO doctorInfo;

    // 医生实际排班情况
    private List<ShiftsRecordVO> ShiftsRecordList;

    public DoctorInfoPO getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(DoctorInfoPO doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public List<ShiftsRecordVO> getShiftsRecordList() {
        return ShiftsRecordList;
    }

    public void setShiftsRecordList(List<ShiftsRecordVO> shiftsRecordList) {
        ShiftsRecordList = shiftsRecordList;
    }
}
