package com.mc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MasterListDTO implements Serializable {
    //  师傅id
    private Long id;
    //  师傅名称
    private String name;
    //  头像
    private String avatar;
    //  手机号码
    private String mobile;
    //  师傅类型
    private String type;
    //    师傅描述
    private String description;
}
