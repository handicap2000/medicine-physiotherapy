package com.mc.dto.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoParam  implements Serializable {

    //   用户id
    private Long id;
    //   用户名
    private String name;
    //  用户密码
    private String password;
}
