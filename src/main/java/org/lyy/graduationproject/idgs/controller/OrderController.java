package org.lyy.graduationproject.idgs.controller;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Request;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.Response;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ResponseUtil;
import org.lyy.graduationproject.idgs.infrastructure.PO.OrderPO;
import org.lyy.graduationproject.idgs.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description: OrderController
 * date: 2023/5/9 1:32
 * author: caugi
 * version: 1.0
 */

@RestController
@RequestMapping("/controller/order")
public class OrderController {
    Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create-order")
    Response createOrder(@RequestBody Request<OrderPO> orderPORequest) {
        Response response;
        try {
            Long num = orderService.createOrder(orderPORequest.getParams());
            if (num != null) {
                response = ResponseUtil.success(num.toString());
            } else {
                response = ResponseUtil.fail();
            }
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }

    @GetMapping("/query-order/{orderId}")
    Response queryOrderDetail(@PathVariable("orderId") Long orderId) {
        Response response;
        try {
            return ResponseUtil.success(orderService.queryOrderDetail(orderId));
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }

    @PutMapping ("/update-order-status/{orderId}/{status}")
    Response updateOrderStatus(@PathVariable("orderId") Long orderId, @PathVariable("status") Integer status) {
        Response response;
        try {
            orderService.updateOrderStatus(orderId, status);
            return ResponseUtil.success();
        } catch (ApplicationException e) {
            logger.error(e.getResult().getMsg(), e.getResult().getCode(), e);
            response = ResponseUtil.fail(e.getResult());
        }
        return response;
    }
}
