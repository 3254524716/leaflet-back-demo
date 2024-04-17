package com.example.leaflet_back_demo.controller;

import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.Feature;
import com.example.leaflet_back_demo.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;


@Slf4j
@RestController
public class FeatureController {
    @Resource
    private FeatureService featureService;

    @Value("${server.port}")
    private String serverPort;

    //查询所有
    @GetMapping(value = "/feature/getfeaturesbyfield")
    //跨域设置
    @CrossOrigin(origins = {"http://localhost:8089", "http://192.168.0.122:8089"})
    public CommonResult getAllPayroll( @RequestParam("column") String column,@RequestParam("value") String value) {
        // 应该返回list
        ArrayList<Feature> payrollList = featureService.getFeaturesByField(column, value);
        log.info("****查询结果: " + payrollList);

        if (payrollList != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payrollList);
        } else {
            return new CommonResult(444, "没有记录" , null);
        }
    }

}
