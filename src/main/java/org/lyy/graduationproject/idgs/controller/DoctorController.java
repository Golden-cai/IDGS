package org.lyy.graduationproject.idgs.controller;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Request;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Response;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ResponseUtil;
import org.lyy.graduationproject.idgs.infrastructure.request.DoctorInfoRequest;
import org.lyy.graduationproject.idgs.service.IDoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * description: DoctorController
 * date: 2023/4/25 22:36
 * author: caugi
 * version: 1.0
 */
@RestController
@RequestMapping("/controller/doctor")
public class DoctorController {
    Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private IDoctorService doctorService;

    @PostMapping("/add-doctor")
    public Response addDoctorForDepartment(@RequestBody Request<DoctorInfoRequest> params) {
        Response response;
        try {
            response = ResponseUtil.success(doctorService.addDoctor(params.getParams()));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }

    @GetMapping("/query-doctor/{doctorId}")
    public Response queryDoctorByDepartmentId(@PathVariable("doctorId") Long doctorId) {
        Response response;
        try {
            response = ResponseUtil.success(doctorService.queryDoctorByDepartmentId(doctorId));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }


}
