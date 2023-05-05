package org.lyy.graduationproject.idgs.infrastructure.request;

import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorShiftsPO;

import java.util.List;

/**
 * description: DoctorShiftsRequest
 * date: 2023/5/1 19:13
 * author: caugi
 * version: 1.0
 */
public class DoctorShiftsRequest {

    private List<DoctorShiftsPO> shifts;

    public List<DoctorShiftsPO> getShifts() {
        return shifts;
    }

    public void setShifts(List<DoctorShiftsPO> shifts) {
        this.shifts = shifts;
    }
}
