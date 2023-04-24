package org.lyy.graduationproject.idgs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lyy.graduationproject.idgs.infrastructure.PO.HospitalInfoPO;

import java.sql.SQLException;
import java.util.List;

/**
 * description: HospitalDao
 * date: 2023/4/16 3:53
 * author: lyy
 * version: 1.0
 */

@Mapper
public interface HospitalDao {

    // 查询所有医院List
    List<HospitalInfoPO> queryAllHospital() throws SQLException;

}
