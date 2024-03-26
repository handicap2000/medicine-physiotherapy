package com.mc.controllrer;

import com.mc.common.api.CommonResult;
import com.mc.dto.OrderListDTO;
import com.mc.dto.BookTimeDTO;
import com.mc.dto.OrderTimeDTO;
import com.mc.dto.param.UserOrderListParam;
import com.mc.po.Order;
import com.mc.service.BookTimeService;
import com.mc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrdersService ordersService;

    @Autowired
    BookTimeService bookTimeService;

    /**
     * 提交用户订单
     *
     * @param orderParam
     * @return
     * @throws IOException
     */
    @PostMapping("/userOrder")
    public CommonResult timePick(@RequestBody Order orderParam) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

//        System.out.println(orderParam.getName());
//        System.out.println(orderParam.getMobile());
//        System.out.println(orderParam.getAddress());
//        System.out.println(orderParam.getRemark());
//        System.out.println(orderParam.getPickTime());
        //try {
        //    System.out.println(sdf.parse(startTime));
        //} catch (ParseException e) {
        //    throw new RuntimeException(e);
        //}
        Order order = new Order();
        order.setName(orderParam.getName());
        order.setMobile(orderParam.getMobile());
        order.setAddress(orderParam.getAddress());
        order.setRemark(orderParam.getRemark());
        order.setPickTime(orderParam.getPickTime());
        order.setUser(orderParam.getUser());
        order.setMaster(orderParam.getMaster());
//        System.out.println(orderParam.toString());
//        System.out.println(orderParam.getUser().getId());
//        System.out.println(orderParam.getMaster().getId());
        Order ord = ordersService.saveOrders(orderParam);
        return CommonResult.success();
    }


    /**
     * 获取所有用户订单
     * @return
     */
    @GetMapping("/allOrderList")
    //public List<Order> orderList(@PathVariable Long id, @PageableDefault(size = 100, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
    public List<OrderListDTO> orderList() {
        Page<OrderListDTO> orderPage = ordersService.listOrders();
        //System.out.println(orderList.toString());
        return orderPage.getContent();
    }

    /**
     * 获取登录用户订单
     *
     * @param userOrderListParam
     * @return
     */
    @PostMapping("/orderUserListById")
    public List<OrderListDTO> orderListByUser(@RequestBody UserOrderListParam userOrderListParam) {
        Page<OrderListDTO> orderListPage = ordersService.getOrderById(userOrderListParam.getUserId());
        return orderListPage.getContent();
    }

    /**
     * 根据订单状态获取用户订单列表，等于
     *
     * @param userOrderListParam
     * @return
     */
    @PostMapping("/orderListByIdAndStatus")
    public List<OrderListDTO> orderListByIdAndStatus(@RequestBody UserOrderListParam userOrderListParam) {
//        System.out.println(userOrderListParam.toString());
        Page<OrderListDTO> orderListPage = ordersService.getOrderByIdAndStatus(userOrderListParam.getUserId(), userOrderListParam.getOrderStatusEnum());
        return orderListPage.getContent();
    }

    /**
     * 根据订单状态获取用户订单列表，不等于
     *
     * @param userOrderListParam
     * @return
     */
    @PostMapping("/orderListByIdAndNotStatus")
    public List<OrderListDTO> orderListByIdAndNotStatus(@RequestBody UserOrderListParam userOrderListParam) {
//        System.out.println(userOrderListParam.toString());
        Page<OrderListDTO> orderListPage = ordersService.getOrderByIdAndNotStatus(userOrderListParam.getUserId(), userOrderListParam.getOrderStatusEnum());
        return orderListPage.getContent();
    }

    /**
     * 根据用户获取订单预约时间
     * @param userOrderListParam
     * @return
     */
    @PostMapping("/userOrderTimeList")
    public List<BookTimeDTO> userOrderTimeList(@RequestBody UserOrderListParam userOrderListParam) {
//        System.out.println(userOrderListParam.toString());
        List<BookTimeDTO> orderTimeListPage = ordersService.getUserOrderTime(userOrderListParam.getMasterId(),userOrderListParam.getOrderStatusEnum(), "%" + userOrderListParam.getPickTime() +"%",userOrderListParam.getUserId());
        //Page<OrderTimeDTO> orderTimeListPage = ordersService.getUserOrderTime(userOrderListParam.getId(), userOrderListParam.getOrderStatusEnum(),  userOrderListParam.getPickTime());
//        System.out.println(orderTimeListPage);
        return orderTimeListPage;
    }
}
