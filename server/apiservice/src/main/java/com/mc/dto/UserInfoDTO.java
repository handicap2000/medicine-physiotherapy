package com.mc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserInfoDTO implements Serializable {
    //    用户id
    private Long id;
    //    用户名
    private String name;
    //   昵称
    private String nick_name;
    //    头像
    private String avatar;
}