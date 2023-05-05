package org.lyy.graduationproject.idgs.controller;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Request;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Response;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ResponseUtil;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorInfoPO;
import org.lyy.graduationproject.idgs.infrastructure.PO.DoctorShiftsPO;
import org.lyy.graduationproject.idgs.infrastructure.request.DoctorShiftsRequest;
import org.lyy.graduationproject.idgs.service.IShiftsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: ShiftsController 班次
 * date: 2023/4/26 1:37
 * author: caugi
 * version: 1.0
 */

@RestController
@RequestMapping("/controller/shifts")
public class ShiftsController {
    Logger logger = LoggerFactory.getLogger(ShiftsController.class);

    @Autowired
    private IShiftsService shiftsService;

    /**
     * @Description: 根据部门ID查询部门下所有医生班次
     *
     * @param  * @param null
     * @return
     * @throws
     */
    @GetMapping("/query-all-shifts/{departmentId}")
    Response queryShiftsByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        Response response;
        try {
            response = ResponseUtil.success(shiftsService.queryShiftsByDepartmentId(departmentId));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }

    @PostMapping("/addShifts")
    Response addShifts(@RequestBody Request<DoctorShiftsRequest> request) {
        Response response;
        try {
            response = ResponseUtil.success(shiftsService.addShifts(request.getParams()));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }
}
