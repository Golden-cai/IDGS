package org.lyy.graduationproject.idgs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;

import javax.print.Doc;
import java.sql.SQLException;
import java.util.List;

/**
 * description: DoctorInfoDao
 * date: 2023/4/25 22:39
 * author: caugi
 * version: 1.0
 */
@Mapper
public interface DoctorInfoDao {

    int addDoctorInfo(List<DoctorInfoPO> doctorInfoPOList) throws SQLException;

    DoctorInfoPO queryDoctorById(Long doctorId) throws SQLException;

    List<DoctorInfoPO> queryAllDoctorByDepartmentId(Long departmentId) throws SQLException;
}
