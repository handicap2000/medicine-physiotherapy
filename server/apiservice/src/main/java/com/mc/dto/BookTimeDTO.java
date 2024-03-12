package com.mc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BookTimeDTO implements Serializable {
    //   id
    private Long id;
    //    预约时间
    private String pickTime;
    //    是否已选
    private Boolean checked;
    //   是否可约
    private Boolean available;
    //    是否已选
    //private BoolEnum boolCheck;
    //    是否可约
    //private BoolEnum boolAvailable;
}
