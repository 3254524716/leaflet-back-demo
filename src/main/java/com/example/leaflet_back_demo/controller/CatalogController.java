package com.example.leaflet_back_demo.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.leaflet_back_demo.entities.Catalog;
import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:8089/","http://192.168.0.122:8089"})//跨域设置
public class CatalogController {
    @Resource
    private CatalogService catalogService;

    @Value("${server.port}")
    private String serverPort;
    //查询目录
    @GetMapping(value = "/catalog/tree")
    public CommonResult getCatalogTree () {
        //第一级目录 顶层目录
        ArrayList<Catalog> catalogData = getCatalog(0);

        log.info("****查询结果: " + catalogData);

        if (catalogData != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, catalogData);
        } else {
            return new CommonResult(444, "没有记录" , null);
        }

    }

    //递归方法
    public ArrayList<Catalog> getCatalog(Integer pid){
        ArrayList<Catalog> catalogArrayList = catalogService.getCatalog(pid);
        JSONObject catalogCurrent = new JSONObject();
        if(catalogArrayList.size()!=0){
            for (Catalog catalog:catalogArrayList) {
                catalogCurrent = JSONObject.from(catalog);
                ArrayList<Catalog> childrenCatalogArray = getCatalog(catalog.getId());
                JSONArray childrenCatalog = JSONArray.from(childrenCatalogArray);
                catalogCurrent.put("children", childrenCatalog);
            }
        }
        //做结果映射
        Catalog catalog = new Catalog();
        catalog.setId(catalogCurrent.getInteger("id"));
        catalog.setName(catalogCurrent.getString("name"));
        catalog.setPid(catalogCurrent.getInteger("pid"));
        catalog.setLevel(catalogCurrent.getInteger("level"));
        catalog.setCount(catalogCurrent.getInteger("count"));
        catalog.setRemark(catalogCurrent.getString("remark"));
        catalog.setPicture(catalogCurrent.getString("picture"));
        catalog.setPictureUrl(catalogCurrent.getString("pictureUrl"));
        catalog.setChildren((ArrayList<Catalog>) catalogCurrent.get("children"));
        ArrayList<Catalog>  arrayListCatalog = new ArrayList<Catalog>();
        arrayListCatalog.add(catalog);
        return arrayListCatalog;
    }
}
