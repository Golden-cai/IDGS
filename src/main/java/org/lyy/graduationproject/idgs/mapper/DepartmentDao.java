package org.lyy.graduationproject.idgs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.DepartmentVO;

import java.sql.SQLException;
import java.util.List;

/**
 * description: DepartmentDao
 * date: 2023/4/23 1:14
 * author: caugi
 * version: 1.0
 */
@Mapper
public interface DepartmentDao {
    /**
     * @Description: 添加医院-科室信息
     *
     * @param departmentPOList 医院-科室信息
     * @return 插入的条数
     * @throws
     */
    int addDepartment(List<DepartmentPO> departmentPOList) throws SQLException;


    /**
     * @Description: 根据hospitalID查询科室
     *
     * @param  hospitalId
     * @return DepartmentVO
     * @throws
     */
    List<DepartmentVO> queryDepartment(Long hospitalId) throws SQLException;


}
