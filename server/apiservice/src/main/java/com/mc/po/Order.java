package com.mc.po;

import com.mc.dto.OrderStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    //    预约人姓名
    @NotBlank(message = "预约姓名不可以为空")
    private String name;
    //    预约时间
    @NotBlank(message = "预约时间不可以为空")
    private String pickTime;
    //    预约开始时间
    //@Temporal(TemporalType.TIMESTAMP)
    //private Date startTime;
    ////    预约结束时间
    //private Date endTime;
    //    联系电话
    @NotBlank(message = "预约电话不可以为空")
    private String mobile;
    //    联系地址
    private String address;
    //    备注
    private String remark;
    //    订单状态
    private OrderStatusEnum status;
    //    创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Master master;
}
