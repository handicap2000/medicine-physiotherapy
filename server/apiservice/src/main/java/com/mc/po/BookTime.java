package com.mc.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_booktime")
public class BookTime {
    @Id
    @GeneratedValue
    private Long id;
    //    预约时间
    private String pickTime;
    //    是否已选
    private Boolean checked;
    //   是否可约
    private Boolean available;
    //    是否已选
    //private BoolEnum boolCheck;
    //   是否可约
    //private BoolEnum boolAvailable;
}
