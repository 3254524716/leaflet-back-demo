package com.example.leaflet_back_demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.sql.Timestamp;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userid;         //用户id
    private String username;        //用户名 (正常是看不到的吧)
    private String loginid;         //登录名 (登录时输入)
    private String password;        //密码
    private String sex;             //用户性别
    private Integer departmentid;   //部门id
    private String phone;           //电话号码
    private Short usertype;       //用户类型
    private Short edittype;       //编辑类型
    private Timestamp jointime;          //添加时间
    private Timestamp lastlogintime;     //最后登录时间
    private Integer logincount;     //登录次数
    private String extra1;          //
    private String extra2;          //
    private Boolean activeflag;     //是否被激活
    private Boolean inuse;          //是否在使用
}
