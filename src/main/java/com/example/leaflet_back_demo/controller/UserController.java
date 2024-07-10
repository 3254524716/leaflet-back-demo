package com.example.leaflet_back_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.User;
import com.example.leaflet_back_demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理
 */

@Slf4j
@RestController
public class UserController {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    UserService userService;

    // 返回用户列表 需要按照 userid 升序排序 可分页
    // 还应该返回共多少页
    // 现在还没有 realname username status 对应的查询
    @GetMapping("user/userlist")
    public CommonResult userList(
            @RequestParam(value="pageSize" ,defaultValue = "10") Integer pageSize ,
            @RequestParam(value="pageNo" , defaultValue = "1") Integer currentPage ,

            @RequestParam(value = "realname" ,required = false) String realname,
            @RequestParam(value = "username" ,required = false) String username,
            @RequestParam(value = "status" ,required = false) Boolean status

    ) {
        Integer offset = (currentPage - 1) * pageSize;
        Integer limit = pageSize;
        try {
            List<User> userList = userService.getUserList(offset, limit);
            Integer count = userService.getUserCount();
            JSONObject  jsonObject = new JSONObject();
            jsonObject.put("totalElements", count);
//            jsonObject.put("totalPage", totalPage);
            jsonObject.put("content",userList);
            return new CommonResult(200, "查询用户列表 成功", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(200, "查询用户列表 失败", e);
        }
    }


    //添加用户
    @PostMapping("/user/add")
    public CommonResult addUser(@RequestBody JSONObject userData) {
        try {
            User user = objectMapper.readValue(userData.toJSONString(), User.class);
            Integer userId = userService.addUser(user);
            return new CommonResult(200, "添加用户 成功", "User added successfully with ID: " + userId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new CommonResult(406, "添加用户 失败", e);
        }
    }


    //编辑用户 修改用户信息
    @PutMapping("/user/edit")
    public CommonResult editUser(@RequestBody JSONObject userData) {
        try {
            // 传入的修改信息 应该包含userid 或者使用登录名 loginid
            User user = objectMapper.readValue(userData.toJSONString(), User.class);
            Integer editUserId = user.getUserid();
            User editUser = userService.getUserByUserId(editUserId);
            if (editUser != null) {
                String message = userService.updateUser(user);
                return new CommonResult(200, "修改用户 成功", message);
            } else {
                return new CommonResult(200, "修改用户 失败", "用户不存在");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new CommonResult(200, "修改用户 失败", e);
        }
    }

    //删除用户
    @DeleteMapping("/user/delete/{userid}")
    public CommonResult deleteUser(@PathVariable Integer userid) {
        try {
            User deleteUser = userService.getUserByUserId(userid);
            if (deleteUser != null) {
                String message = userService.deteleUser(userid);
                return new CommonResult(200, "删除用户 成功", message);
            } else {
                return new CommonResult(200, "删除用户 失败", "用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(200, "删除用户 失败", e);
        }
    }
}
