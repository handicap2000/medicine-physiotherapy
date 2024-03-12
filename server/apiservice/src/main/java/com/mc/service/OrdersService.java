package com.mc.service;

import com.mc.dto.*;
import com.mc.po.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface OrdersService {
    //    保存订单数据
    Order saveOrders(Order order);

    //    获取所有订单
    Page<OrderListDTO> listOrders();

    //    根据用户获取订单
    Page<OrderListDTO> getOrderById(Long id);

    //    根据用户和状态获取订单，等于
    Page<OrderListDTO> getOrderByIdAndStatus(Long id, OrderStatusEnum orderStatusEnum);

    //    根据用户和状态获取订单，不等于
    Page<OrderListDTO> getOrderByIdAndNotStatus(Long id, OrderStatusEnum orderStatusEnum);

    //     获取用户的订单预约时间
    List<BookTimeDTO> getUserOrderTime(Long masterId, OrderStatusEnum orderStatusEnum, String pickTime,Long UserId);

    //    修改订单状态
    Integer changeOrderStatus(OrderStatusEnum status, Long id);

    //    删除订单
    void deleteOrder(Long id);

}
