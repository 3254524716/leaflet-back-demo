package com.example.leaflet_back_demo.service.impl;

import com.example.leaflet_back_demo.dao.UserDao;
import com.example.leaflet_back_demo.entities.User;
import com.example.leaflet_back_demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserByLoginId(String loginId) {
        return userDao.getUserByLoginId(loginId);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public Integer getUserCount() { return userDao.getUserCount(); }

    @Override
    public List<User> getUserList(Integer offset, Integer limit) {
        return userDao.getUserList(offset,limit);
    }

    @Override
    public Integer addUser(User userData) {
        return userDao.addUser(userData);
    }

    @Override
    public String updateUser(User userData) {
        return userDao.updateUser(userData);
    }

    @Override
    public String deteleUser(Integer userId) {
        return userDao.deteleUser(userId);
    }


}
