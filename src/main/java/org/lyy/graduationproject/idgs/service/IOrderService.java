package org.lyy.graduationproject.idgs.service;

import org.lyy.graduationproject.idgs.infrastructure.BaseVO.ApplicationException;
import org.lyy.graduationproject.idgs.infrastructure.PO.OrderPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.OrderDetailVO;

/**
 * description: IOrderService
 * date: 2023/5/9 1:41
 * author: caugi
 * version: 1.0
 */
public interface IOrderService {

    Long createOrder(OrderPO orderPO) throws ApplicationException;

    OrderDetailVO queryOrderDetail(Long orderId) throws ApplicationException;

    void updateOrderStatus(Long orderId, Integer status) throws ApplicationException;
}
