package com.example.leaflet_back_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.Layer;
import com.example.leaflet_back_demo.service.LayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 图层信息查询
 * */

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:8089/",
        "http://192.168.0.122:8089",
        "http://192.168.0.122:8088",
        "http://localhost:8088"},
        allowCredentials = "true")//跨域设置
public class LayerController {
    @Resource
    private LayerService layerService;
    @Value("${server.port}")
    private String serverPort;

    //根据目录查询 目录下图层信息 也可返回图层树(目录设为0)
    @GetMapping(value = "/layer/info")
    public CommonResult getLayersByCatalogId(@RequestParam Integer catalogId) {
        ArrayList<Layer> layerData = new ArrayList<Layer>();
        layerData =  layerService.getLayersByCatalogId(catalogId);
        JSONObject layerResult = new JSONObject();
        layerResult.put("data",layerData);
        layerResult.put("count",layerData.size());

        log.info("****查询结果: " + layerData);

        if (layerResult != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, layerResult);
        } else {
            return new CommonResult(444, "没有记录", null);
        }
    }

    //根据图层id  目录下图层信息
//    public CommonResult getLayerByLayerId(@RequestParam Integer LayerId) {
//        Layer layerData = new Layer();
//        layerData =  layerService.getLayerByLayerId(LayerId);
//
//        JSONObject layerResult = new JSONObject();
//        layerResult.put("data",layerData);
//        layerResult.put("count",layerData.size());
//
//        log.info("****查询结果: " + layerData);
//
//        if (layerResult != null) {
//            return new CommonResult(200, "查询成功,serverPort:" + serverPort, layerResult);
//        } else {
//            return new CommonResult(444, "没有记录", null);
//        }
//    }
}
