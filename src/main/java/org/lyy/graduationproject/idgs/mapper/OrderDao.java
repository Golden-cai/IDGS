package org.lyy.graduationproject.idgs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lyy.graduationproject.idgs.infrastructure.PO.OrderPO;
import org.lyy.graduationproject.idgs.infrastructure.VO.OrderDetailVO;

import java.sql.SQLException;

/**
 * description: OrderDao
 * date: 2023/5/9 1:59
 * author: caugi
 * version: 1.0
 */
@Mapper
public interface OrderDao {

    int addOrder(OrderPO orderPO) throws SQLException;

    OrderDetailVO queryOrderById(Long orderId) throws SQLException;

    int updateOrderStatus(@Param("orderId") Long orderId, @Param("status") Integer status) throws SQLException;
}
