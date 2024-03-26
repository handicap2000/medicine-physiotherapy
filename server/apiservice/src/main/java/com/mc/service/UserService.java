package com.mc.service;

import com.mc.dto.UserInfoDTO;
import com.mc.po.User;

public interface UserService {
    //    检查用户有效
    User checkUser(String name, String password);

    //    根据用户名name查找用户id
    UserInfoDTO getUserIdByName(String name);

    //保存用户
    User saveUser(User user);

    //    修改用户密码
    Integer updateUserPassword(Long id, String newPassword);
}
