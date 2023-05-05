package org.lyy.graduationproject.idgs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorShiftsPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.ShiftsRecordVO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * description: DoctorShiftsDao
 * date: 2023/4/30 15:23
 * author: caugi
 * version: 1.0
 */
@Mapper
public interface DoctorShiftsDao {

    List<DoctorShiftsPO> queryDoctorShifts(@Param("doctorId") Long doctorId, @Param("status") String status) throws SQLException;

    void updateShiftsStatusByShiftId(@Param("shiftsId") Long shiftsId, @Param("status") String status) throws SQLException;

    void updateShiftsStatusNotUse(Long doctorId) throws SQLException;

    int addShifts(List<DoctorShiftsPO> DoctorShiftsPOs) throws SQLException;

    ShiftsRecordVO queryDoctorShiftRecord(@Param("shiftsId") Long shiftsId, @Param("time") Date time) throws SQLException;

    int addShiftsRecord(ShiftsRecordVO shiftsRecordVO) throws SQLException;



}
