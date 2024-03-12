package com.mc.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_shop")
public class Shop {
    @Id
    @GeneratedValue
    private Long id;
//    店铺名称
    private String name;
//  店铺地址
    private String address;
}
