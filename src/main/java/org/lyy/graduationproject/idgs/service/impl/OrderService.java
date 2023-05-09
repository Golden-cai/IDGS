package org.lyy.graduationproject.idgs.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.BaseVO.WebResultEnum;
import org.lyy.graduationproject.idgs.infrastructure.PO.OrderPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.OrderDetailVO;
import org.lyy.graduationproject.idgs.mapper.OrderDao;
import org.lyy.graduationproject.idgs.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * description: OrderService
 * date: 2023/5/9 1:45
 * author: caugi
 * version: 1.0
 */

@Service
public class OrderService implements IOrderService {
    Logger logger = LoggerFactory.getLogger(ShiftsService.class);

    @Autowired
    private OrderDao orderDao;

    private final Snowflake snowFlake = IdUtil.getSnowflake();
    @Override
    public Long createOrder(OrderPO orderPO) throws ApplicationException {
        if (orderPO == null) {
            logger.error("createOrder: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            checkOrderInvalid(orderPO);
            orderPO.setOrderId(snowFlake.nextId());
            orderPO.setOrderStatus(1);
            orderPO.setCreateBy(-1L);
            orderPO.setLastUpdateBy(-1L);
            orderDao.addOrder(orderPO);
            return orderPO.getOrderId();
        } catch (SQLException e) {
            logger.error("createOrder: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }

    @Override
    public OrderDetailVO queryOrderDetail(Long orderId) throws ApplicationException {
        if (orderId == null) {
            logger.error("queryOrderDetail: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            return orderDao.queryOrderById(orderId);
        } catch (SQLException e) {
            logger.error("queryOrderDetail: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }
    }

    @Override
    public void updateOrderStatus(Long orderId, Integer status) throws ApplicationException {
        if (orderId == null) {
            logger.error("updateOrderStatus: params is null");
            throw new ApplicationException(WebResultEnum.PARAM_NULL);
        }
        try {
            orderDao.updateOrderStatus(orderId, status);
        } catch (SQLException e) {
            logger.error("queryOrderDetail: sql exception", e);
            throw new ApplicationException(WebResultEnum.SQL_EXCEPTION);
        }


    }

    private void checkOrderInvalid(OrderPO orderPO) throws ApplicationException  {
        if (orderPO.getUserId() == null) {
            throw new ApplicationException(WebResultEnum.PARAM_ERROE);
        }
        if (orderPO.getShiftsRecordId() == null) {
            throw new ApplicationException(WebResultEnum.PARAM_ERROE);
        }
        if (orderPO.getOrderAmount() == null) {
            throw new ApplicationException(WebResultEnum.PARAM_ERROE);
        }
    }
}
