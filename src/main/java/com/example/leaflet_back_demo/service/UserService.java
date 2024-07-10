package com.example.leaflet_back_demo.service;

import com.example.leaflet_back_demo.entities.User;

import java.util.List;

public interface UserService {
    // 登录名查询 返回用户
    User getUserByLoginId(String loginId);
    // 登录名查询 返回用户
    User getUserByUserId(Integer userId);

    Integer getUserCount();

    List<User> getUserList(Integer offset, Integer limit);
    // 添加用户
    Integer addUser(User userData);
    // 更新用户
    String updateUser(User userData);
    // 删除用户
    String deteleUser(Integer userId);
}
