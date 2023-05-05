package org.lyy.graduationproject.idgs.infrastructure.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.lyy.graduationproject.idgs.infrastructure.PO.ShiftsRecordPO;

/**
 * description: ShiftsRecordVO
 * date: 2023/5/4 1:10
 * author: caugi
 * version: 1.0
 */
public class ShiftsRecordVO extends ShiftsRecordPO {

    /**
     * 医生id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long doctorId;

    /**
     * 值班日期,按周计算
     */
    private Integer shiftsDay;

    /**
     * 最大挂号量
     */
    private Integer shiftsTotalNumber;

    /**
     * 挂号费
     */
    private Double shiftsAmount;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getShiftsDay() {
        return shiftsDay;
    }

    public void setShiftsDay(Integer shiftsDay) {
        this.shiftsDay = shiftsDay;
    }

    public Integer getShiftsTotalNumber() {
        return shiftsTotalNumber;
    }

    public void setShiftsTotalNumber(Integer shiftsTotalNumber) {
        this.shiftsTotalNumber = shiftsTotalNumber;
    }

    public Double getShiftsAmount() {
        return shiftsAmount;
    }

    public void setShiftsAmount(Double shiftsAmount) {
        this.shiftsAmount = shiftsAmount;
    }
}
