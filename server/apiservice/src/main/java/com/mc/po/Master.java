package com.mc.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_master")
public class Master {
    @Id
    @GeneratedValue
    private Long id;
    //  师傅名称
    @NotBlank(message = "师傅姓名不可以为空")
    private String name;
    //  头像
    private String avatar;
    //  师傅类型
    @NotBlank(message = "手机号不可以为空")
    //  手机号码
    private String mobile;
    //    师傅类型
    @NotBlank(message = "类型不可以为空")
    private String type;
    //    师傅描述
    private String description;
    //    创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "master")
    private List<Order> orders = new ArrayList<Order>();

    @ManyToMany
    private List<User> users = new ArrayList<User>();
}
