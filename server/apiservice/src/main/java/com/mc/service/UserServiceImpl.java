package com.mc.service;


import com.mc.dao.UserRepository;
import com.mc.dto.UserInfoDTO;
import com.mc.po.User;
import com.mc.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PageUtils pageUtils;

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    @Override
    public UserInfoDTO getUserIdByName(String name) {
        return userRepository.getUserId(name);
    }

    @Override
    public User saveUser(User user) {
        user.setCreateTime(new Date());
        return userRepository.save(user);
    }

    @Override
    public Integer updateUserPassword(Long id, String newPassword) {
        return userRepository.updateUserPassword(id, newPassword);
    }
}
