package com.example.leaflet_back_demo.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userid;            //图层id
    private String loginid;        //图层名
    private String password; //描述
    private String username;
    private String sex;      //图层名 (请求时用的 图层名)
    private Integer departmentid;         //地址 (放从哪请求)
    private String phone;  //该图层框
    private Integer usertype;         //该图层坐标系
    private Integer edittype;
    private Date jointime;        //图层数据类型
    private Date lastlogintime;
    private Integer logincount; //该图层所在 图层目录
    private String extra1; //该图层所在 图层目录
    private String extra2; //该图层所在 图层目录
    private boolean activeflag; //该图层所在 图层目录
    private boolean inuse; //该图层所在 图层目录
}
