package org.lyy.graduationproject.idgs.service;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.PO.HospitalInfoPO;
import org.lyy.graduationproject.idgs.infrastructure.Response.DepartmentResponse;

import java.util.List;

/**
 * description: IHospitalService
 * date: 2023/4/16 20:49
 * author: lyy
 * version: 1.0
 */
public interface IHospitalService {

    /**
     * @Description: 获取所有医院列表
     *
     * @return 医院名称列表
     * @throws ApplicationException
     */
     List<HospitalInfoPO>  queryAllHospital() throws ApplicationException;

     /**
      * @Description: 为指定的hospital添加全量科室信息
      *
      * @param hospitalId 医院id
      * @return int 创建的科室条数
      * @throws
      */
     int addAllDepartmentForHospital(Long hospitalId) throws ApplicationException;

     /**
      * @Description: 查询hospitalId下的所有科室
      *
      * @param hospitalId 科室id
      * @return 科室详情List
      * @throws
      */
     List<DepartmentResponse> queryAllAllDepartmentByHospital(Long hospitalId) throws ApplicationException;
}
