package com.example.leaflet_back_demo.controller;

import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.Feature;
import com.example.leaflet_back_demo.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;


@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:8089/","http://192.168.0.122:8089"})//跨域设置
public class FeatureController {
    @Resource
    private FeatureService featureService;

    @Value("${server.port}")
    private String serverPort;

    //空间查询
    @GetMapping(value = "/feature/getfeaturesbyfield")
    public CommonResult getFeatureByField ( @RequestParam("column") String column,@RequestParam("value") String value) {
        // 应该返回list
        ArrayList<Feature> featureList = featureService.getFeaturesByField(column, value);
        log.info("****查询结果: " + featureList);

        if (featureList != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, featureList);
        } else {
            return new CommonResult(444, "没有记录" , null);
        }
    }

    @PostMapping(value = "/feature/getfeaturesbyspace")
    public CommonResult getFeatureBySpace (@RequestBody String polygon) {
        System.out.println("看看请求参数，" + polygon);
        ArrayList<Feature> featureList = featureService.getFeatureBySpace(polygon);
        log.info("****查询结果: " + featureList);
        if (featureList != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, featureList);
        } else {
            return new CommonResult(444, "没有记录" , null);
        }
    }



}
