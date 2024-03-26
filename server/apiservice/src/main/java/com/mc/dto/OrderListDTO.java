package com.mc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OrderListDTO implements Serializable {
    //   订单id
    private Long id;
    //    预约人姓名
    private String name;
    //     预约师傅姓名
    private String masterName;
    //    预约时间
    private String pickTime;
    //    预约开始时间
    //@Temporal(TemporalType.TIMESTAMP)
    //private Date startTime;
    ////    预约结束时间
    //private Date endTime;
    //    联系电话
    private String mobile;
    //    联系地址
    private String address;
    //    备注
    private String remark;
//    订单状态
    private OrderStatusEnum status;
}
