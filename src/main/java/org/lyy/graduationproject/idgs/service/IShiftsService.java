package org.lyy.graduationproject.idgs.service;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.VO.DoctorShiftsRecordVO;
import org.lyy.graduationproject.idgs.infrastructure.request.DoctorShiftsRequest;

import java.util.List;

/**
 * description: IShiftsService 班次service
 * date: 2023/4/30 15:02
 * author: caugi
 * version: 1.0
 */
public interface IShiftsService {
    /**
     * @Description: 查询部门下的所有班次
     *
     * @params 部门id
     * @return 医院名称列表
     * @throws ApplicationException
     */
    List<DoctorShiftsRecordVO> queryShiftsByDepartmentId(Long departmentId) throws ApplicationException;

    /**
     * @Description: 添加新的班次
     *
     * @param DoctorShifts
     * @return 添加成功的数量
     * @throws ApplicationException
     */
    int addShifts(DoctorShiftsRequest DoctorShifts) throws ApplicationException;
}
