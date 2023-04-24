package org.lyy.graduationproject.idgs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentItemPO;

import java.sql.SQLException;
import java.util.List;

/**
 * description: DepartmentItemDao
 * date: 2023/4/22 23:36
 * author: caugi
 * version: 1.0
 */

@Mapper
public interface DepartmentItemDao {

    int addDepartmentItem(List<DepartmentItemPO> departmentItemPOList) throws SQLException;

    List<DepartmentItemPO> queryAllDepartmentItem() throws SQLException;
}
