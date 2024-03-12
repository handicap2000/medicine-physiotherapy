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
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    //   用户id
    private Long id;
    //   微信唯一用户id
    private String open_id;
    //    用户名
    @NotBlank(message = "姓名不可以为空")
    @Column(unique = true)
    private String name;
    //    昵称
    private String nick_name;
    //    密码
    @NotBlank(message = "密码不可以为空")
    private String password;
    //   角色
    private String role;
    //    创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //    头像
    private String avatar;
    //
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();

    @ManyToMany
    private List<Master> masters = new ArrayList<Master>();
}
