package org.lyy.graduationproject.idgs.controller;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Response;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ResponseUtil;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Result;
import org.lyy.graduationproject.idgs.service.IHospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description: HospitalController
 * date: 2023/4/16 20:38
 * author: lyy
 * version: 1.0
 */
@RestController
@RequestMapping("/controller/hospital")
public class HospitalController {
    Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @Autowired
    private IHospitalService hospitalService;

    @GetMapping ("/query-all-hospital")
    public Response queryAllHospitalList() {
        Response response;
        try {
            response = ResponseUtil.success(hospitalService.queryAllHospital());
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }

    @GetMapping ("/add-all-department/{hospitalId}")
    public Response addAllDepartmentForHospital(@PathVariable("hospitalId") Long hospitalId) {
        Response response;
        try {
            response = ResponseUtil.success(hospitalService.addAllDepartmentForHospital(hospitalId));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }


    @GetMapping("/query-all-department/{hospitalId}")
    public Response queryAllDepartmentByHospitalId(@PathVariable("hospitalId") Long hospitalId) {
        Response response;
        try {
            response = ResponseUtil.success(hospitalService.queryAllAllDepartmentByHospital(hospitalId));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }
}
