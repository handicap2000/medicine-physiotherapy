package com.mc.dao;

import com.mc.dto.UserInfoDTO;
import com.mc.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;


public interface UserRepository extends JpaRepository<User, Long> {
    //  根据名字和密码查找
    User findByNameAndPassword(String name, String password);

    //  获取用户id
    @Query("select new com.mc.dto.UserInfoDTO(u.id,u.name,u.nick_name,u.avatar) from User u where u.name = ?1")
    UserInfoDTO getUserId(String name);

    //  修改密码
    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.id = ?1")
    int updateUserPassword(Long userId, String newPassword);
}
