package com.example.leaflet_back_demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
//目录树结点
public class Catalog {
    private Integer id;        //当前目录 id

    private String name;    //当前目录 名称

    private Integer pid;        //当前目录 父结点id

    private Integer level;        //当前目录 级别

    private Integer count;        //当前目录 (包含图层数 或 子目录数) 先留用

    private String remark;        //当前目录 描述

    private String picture;        //当前目录 图像 (留用)
    @JsonProperty("pictureUrl")
    private String pictureUrl;      //当前目录 图像地址 (留用)

    private ArrayList<Catalog> children; //当前目录 子项 不知道能不能做映射 改为查回来 转json再添加
}
