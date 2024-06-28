package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    // 根据用户名 或 id 查出用户信息
    // 或者直接用 用户名和密码 查询表格 判断有没有对用信息
    User getUserByUserName(String layerId);
}
