package com.example.leaflet_back_demo.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.leaflet_back_demo.entities.Catalog;
import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.Layer;
import com.example.leaflet_back_demo.service.CatalogService;
import com.example.leaflet_back_demo.service.LayerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 目录 图层树
 * */

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:8089/",
        "http://192.168.0.122:8089",
        "http://192.168.0.122:8088",
        "http://localhost:8088"},
        allowCredentials = "true")//跨域设置
public class CatalogController {
    @Resource
    private CatalogService catalogService;
    @Resource
    private LayerService layerService;

    @Value("${server.port}")
    private String serverPort;
    //查询目录
    @GetMapping(value = "/catalog/tree")
    public CommonResult getCatalogTree (@RequestParam(required = false) Integer level) {
        //第一级目录 顶层目录
        ArrayList<Catalog> catalogData = new ArrayList<Catalog>();
        if(level==null){
            catalogData = getCatalog(0,(a) -> catalogService.getCatalog(0));
        }else{
            catalogData = getCatalog(level,(a) -> catalogService.getLevelCatalog(a));
        }

        log.info("****查询结果: " + catalogData);

        if (catalogData != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, catalogData);
        } else {
            return new CommonResult(444, "没有记录" , null);
        }
    }


    //递归方法
    public ArrayList<Catalog> getCatalog(Integer pidOrLevel,GetCatalog catalogFunction){
        ArrayList<Catalog> catalogArrayList = catalogFunction.getCatalog(pidOrLevel);
//        ArrayList<Catalog> catalogArrayList = catalogService.getCatalog(pid);
        JSONObject catalogCurrent = new JSONObject();
        ArrayList<Catalog>  arrayListCatalog = new ArrayList<Catalog>();
        if(catalogArrayList.size()!=0){
            for (Catalog catalog:catalogArrayList) {
                catalogCurrent = JSONObject.from(catalog);
                ArrayList<Catalog> childrenCatalogArray = getCatalog(catalog.getId(), (a) -> catalogService.getCatalog(a));
                JSONArray childrenCatalog = JSONArray.from(childrenCatalogArray) ;
                ArrayList<Layer>  layers = new ArrayList<Layer>();
                if(childrenCatalog.size()==0){
                    layers = layerService.getLayersByCatalogId(catalog.getId());
                    catalogCurrent.put("children", layers);
                }else{
                    catalogCurrent.put("children", childrenCatalog);
                }
                Catalog currentCatalog = new Catalog();
                currentCatalog.setId(catalogCurrent.getInteger("id"));
                currentCatalog.setName(catalogCurrent.getString("name"));
                currentCatalog.setPid(catalogCurrent.getInteger("pid"));
                currentCatalog.setLevel(catalogCurrent.getInteger("level"));
                currentCatalog.setCount(catalogCurrent.getInteger("count"));
                currentCatalog.setRemark(catalogCurrent.getString("remark"));
                currentCatalog.setFolder(catalogCurrent.getString("folder"));
                currentCatalog.setPicture(catalogCurrent.getString("picture"));
                currentCatalog.setPictureUrl(catalogCurrent.getString("pictureUrl"));
                currentCatalog.setChildren((ArrayList<Catalog>) catalogCurrent.get("children"));
                arrayListCatalog.add(currentCatalog);
            }
        }
        //做结果映射
        return arrayListCatalog;
    }

    interface GetCatalog {
        ArrayList<Catalog> getCatalog(Integer pidOrLevel);
    }
}
