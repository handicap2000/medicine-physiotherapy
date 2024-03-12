package com.mc.service;

import com.mc.dao.BookTimeRespository;
import com.mc.dao.OrdersRespository;
import com.mc.dto.OrderListDTO;
import com.mc.dto.OrderStatusEnum;
import com.mc.dto.BookTimeDTO;
import com.mc.dto.OrderTimeDTO;
import com.mc.po.BookTime;
import com.mc.po.Order;
import com.mc.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    PageUtils pageUtils;
//    PageUtils pageUtils = new PageUtils();

    @Autowired
    OrdersRespository ordersRespository;

    @Autowired
    BookTimeRespository bookTimeRespository;

    /**
     * 保存订单
     *
     * @param order
     * @return
     */
    @Override
    public Order saveOrders(Order order) {
        order.setCreateTime(new Date());
        order.setStatus(OrderStatusEnum.UNUSED);
        return ordersRespository.save(order);
    }

    /**
     * 订单列表
     *
     * @return
     */
    @Override
    public Page<OrderListDTO> listOrders() {
        return ordersRespository.getAllOrder(pageUtils.pageOrderByCreateTime());
    }

    /**
     * 获取用户订单列表
     *
     * @param userId
     * @return
     */
    @Override
    public Page<OrderListDTO> getOrderById(Long userId) {
        //Sort sort = Sort.by(Sort.Order.desc("createTime"));
        //Pageable pageable = PageRequest.of(0, size, sort);
        return ordersRespository.getOrderById(pageUtils.pageOrderByCreateTime(), userId);
    }

    /**
     * 根据用户id和订单状态获取订单列表，等于
     *
     * @param userId
     * @param orderStatusEnum
     * @return
     */
    @Override
    public Page<OrderListDTO> getOrderByIdAndStatus(Long userId, OrderStatusEnum orderStatusEnum) {
        //Sort sort = Sort.by(Sort.Order.desc("createTime"));
        //Pageable pageable = PageRequest.of(0, size, sort);
        return ordersRespository.getOrderByIdAndStatus(pageUtils.pageOrderByCreateTime(), userId, orderStatusEnum);
    }

    /**
     * 根据用户id和订单状态获取订单列表，不等于
     *
     * @param id
     * @param orderStatusEnum
     * @return
     */
    public Page<OrderListDTO> getOrderByIdAndNotStatus(Long userId, OrderStatusEnum orderStatusEnum) {
        //Sort sort = Sort.by(Sort.Order.desc("createTime"));
        //Pageable pageable = PageRequest.of(0, size, sort);
        return ordersRespository.getOrderByIdAndNotStatus(pageUtils.pageOrderByCreateTime(), userId, orderStatusEnum);
    }

    /**
     * 获取用户的订单预约时间
     *
     * @param userId
     * @param masterId
     * @param orderStatusEnum
     * @param pickTime
     * @return
     */
    @Override
    public List<BookTimeDTO> getUserOrderTime(Long masterId, OrderStatusEnum orderStatusEnum, String pickTime, Long userId) {
        List<BookTimeDTO> bookTimeList = bookTimeRespository.getBookTime();
        List<OrderTimeDTO> orderTimeList = ordersRespository.getUserOrderTime(pageUtils.pageOrderByCreateTime(), masterId, orderStatusEnum, pickTime, userId).getContent();
        for (BookTimeDTO bookTime : bookTimeList) {
            for (OrderTimeDTO orderTime : orderTimeList) {
                if (orderTime.getPickTime().contains(bookTime.getPickTime())) {
//                    System.out.println(bookTime.getPickTime());
                    bookTime.setAvailable(false);

                }
            }
        }
//        System.out.println(orderTimeList.toString());
//        System.out.println(bookTimeList.toString());
        return bookTimeList;
//        return ordersRespository.getUserOrderTime(pageUtils.pageOrderByCreateTime(), id, orderStatusEnum, pickTime);
    }


    /**
     * 修改用户的订单状态
     *
     * @param status
     * @param id
     * @return
     */
    @Override
    public Integer changeOrderStatus(OrderStatusEnum status, Long id) {
        return ordersRespository.cancelOrder(status, id);
    }


    /**
     * 删除订单
     *
     * @param id
     */
    @Override
    public void deleteOrder(Long id) {
        ordersRespository.deleteById(id);
    }
}
