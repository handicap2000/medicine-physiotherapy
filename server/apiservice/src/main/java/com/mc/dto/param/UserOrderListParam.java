package com.mc.dto.param;

import com.mc.dto.OrderStatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserOrderListParam implements Serializable {
    //    用户的Id
    private Long userId;
    //    师傅的Id
    private Long masterId;
    //    用户订单状态
    private OrderStatusEnum orderStatusEnum;
    //    预约时间
    private String pickTime;
}
