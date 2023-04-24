package org.lyy.graduationproject.idgs.controller;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Request;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Response;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ResponseUtil;
import org.lyy.graduationproject.idgs.infrastructure.PO.DepartmentItemPO;
import org.lyy.graduationproject.idgs.infrastructure.request.DepartmentItemRequest;
import org.lyy.graduationproject.idgs.service.IDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: DepartmentController
 * date: 2023/4/22 1:42
 * author: caugi
 * version: 1.0
 */
@RestController
@RequestMapping("/controller/department")
public class DepartmentController {
    Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private IDepartmentService departmentService;

    @PostMapping("/add-department-Item")
    public Response addDepartmentItem(@RequestBody Request<DepartmentItemRequest> params) {
        Response response;
        try {
            response = ResponseUtil.success(departmentService.addDepartmentItem(params.getParams()));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }
}
