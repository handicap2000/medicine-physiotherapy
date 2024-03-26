package com.mc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OrderTimeDTO implements Serializable {
    //    预约时间
    private String pickTime;
}
