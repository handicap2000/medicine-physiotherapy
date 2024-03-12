package com.mc.dao;

import com.mc.dto.OrderListDTO;
import com.mc.dto.OrderStatusEnum;
import com.mc.dto.OrderTimeDTO;
import com.mc.po.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface OrdersRespository extends JpaRepository<Order, Long> {
    //   查找所有订单
    @Query("select  new com.mc.dto.OrderListDTO (o.id,o.name,m.name,o.pickTime,o.mobile,o.address,o.remark,o.status) from Order o, Master m where  o.master.id = m.id")
    Page<OrderListDTO> getAllOrder(Pageable pageable);

    //    根据用户查找订单
    @Query("select  new com.mc.dto.OrderListDTO (o.id,o.name,m.name,o.pickTime,o.mobile,o.address,o.remark,o.status) from Order o, Master m  where o.user.id = ?1 and  o.master.id = m.id")
    Page<OrderListDTO> getOrderById(Pageable pageable, Long id);

    //    根据用户和订单状态查找订单
    @Query("select  new com.mc.dto.OrderListDTO (o.id,o.name,m.name,o.pickTime,o.mobile,o.address,o.remark,o.status) from Order o, Master m  where o.user.id = ?1 and o.status = ?2 and  o.master.id = m.id")
    Page<OrderListDTO> getOrderByIdAndStatus(Pageable pageable, Long id, OrderStatusEnum orderStatusEnum);

    //    根据用户和订单状态查找订单
    @Query("select  new com.mc.dto.OrderListDTO (o.id,o.name,m.name,o.pickTime,o.mobile,o.address,o.remark,o.status) from Order o, Master m  where o.user.id = ?1 and o.status != ?2 and  o.master.id = m.id")
    Page<OrderListDTO> getOrderByIdAndNotStatus(Pageable pageable, Long id, OrderStatusEnum orderStatusEnum);

    //   根据用户返回预约的日期
//    @Query("select  new com.mc.dto.OrderTimeDTO (o.pickTime) from Order o where o.user.id = ?1 and o.master.id = ?2 and o.status = ?3 and o.pickTime like ?4")
    @Query("select  new com.mc.dto.OrderTimeDTO (o.pickTime) from Order o where o.master.id = ?1 and o.status = ?2 and o.pickTime like ?3")
    Page<OrderTimeDTO> getUserOrderTime(Pageable pageable, Long masterId,OrderStatusEnum orderStatusEnum, String pickTime, Long UserId);

    //修改订单状态
    @Transactional
    @Modifying
    @Query("update Order o set o.status = ?1 where o.id = ?2")
    int cancelOrder(OrderStatusEnum status, Long id);
    //    根据用户查找订单
    //List<Order> FindOrderById(Long id);

}
