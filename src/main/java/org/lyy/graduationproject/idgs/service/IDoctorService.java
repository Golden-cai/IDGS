package org.lyy.graduationproject.idgs.service;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;
import org.lyy.graduationproject.idgs.infrastructure.request.DoctorInfoRequest;

/**
 * description: IDoctorService
 * date: 2023/4/25 22:43
 * author: caugi
 * version: 1.0
 */
public interface IDoctorService {
    /**
     * @Description: 批量添加医生
     *
     * @param request 医生信息
     * @return 插入成功的数量
     * @throws ApplicationException
     */
    int addDoctor(DoctorInfoRequest request) throws ApplicationException;

    /**
     * @Description: 根据医生id查询医生详细信息
     *
     * @param doctorId 医生id
     * @return 医生详情
     * @throws
     */
    DoctorInfoPO queryDoctorByDepartmentId(Long doctorId) throws ApplicationException;

}
