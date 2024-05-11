package com.example.leaflet_back_demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.Feature;
import com.example.leaflet_back_demo.entities.Layer;
import com.example.leaflet_back_demo.service.FeatureService;
import com.example.leaflet_back_demo.service.LayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;



@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:8089/", "http://192.168.0.122:8089"})//跨域设置
public class FeatureController {
    @Resource
    private FeatureService featureService;
    @Resource
    private LayerService layerService;

    @Value("${server.port}")
    private String serverPort;

    //属性查询
//    @GetMapping(value = "/feature/getfeaturesbyfield")
//    public CommonResult getFeatureByField ( @RequestParam("column") String column,@RequestParam("value") String value) {
//        // 应该返回list
//        ArrayList<Feature> featureList = featureService.getFeaturesByField(column, value);
//        log.info("****查询结果: " + featureList);
//
//        if (featureList != null) {
//            return new CommonResult(200, "查询成功,serverPort:" + serverPort, featureList);
//        } else {
//            return new CommonResult(444, "没有记录" , null);
//        }
//    }

    //空间查询
//    @PostMapping(value = "/feature/getfeaturesbyspace")
//    public CommonResult getFeatureBySpace(@RequestBody String polygon) {
//        System.out.println("看看请求参数，" + polygon);
//        ArrayList<Feature> featureList = featureService.getFeatureBySpace(polygon);
//        log.info("****查询结果: " + featureList);
//        if (featureList != null) {
//            return new CommonResult(200, "查询成功,serverPort:" + serverPort, featureList);
//        } else {
//            return new CommonResult(444, "没有记录", null);
//        }
//    }

    @PostMapping(value = "/feature/getfeaturesbyspace")
    public CommonResult getFeatureBySpace(@RequestBody JSONObject requestData) {
        System.out.println("requestData "+requestData);

        String polygon = requestData.getString("polygon");
        JSONArray layerIds = new JSONArray();
        if(requestData.getJSONArray("layerIds")!=null){
            layerIds = requestData.getJSONArray("layerIds");
        }
        JSONArray resultArray = new JSONArray();
        if (!layerIds.isEmpty()) {
            for ( Object layerId : layerIds) {
                System.out.println("Object layerId  "+ layerId);
                Layer layerData = new Layer();
                layerData = layerService.getLayerByLayerId(layerId.toString());
                //用来查要素表的 图层名
                String layer = layerData.getLayers() == null ? null : layerData.getLayers().split(":")[1];
                System.out.println(layer + "   layerNamelayerNamelayerName");
                if(layer!=null){
                    JSONObject layerInfo = new JSONObject();
                    layerInfo.put("layerName", layerData.getName());
                    layerInfo.put("id", layerId);
                    ArrayList<Feature> featureList;
                    try {
                         featureList = featureService.getFeatureBySpaceAndLayer(layer, polygon);
                    } catch (Exception e) {
                        e.printStackTrace(); // 打印异常信息
                        featureList = new ArrayList<Feature>();
                    }
                    JSONObject result = new JSONObject();
                    result.put("data", featureList);
                    result.put("layer", layerInfo);
                    resultArray.add(result);
                }
            }
        }
        log.info("****查询结果: " + resultArray);
        if (resultArray != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, resultArray);
        } else {
            return new CommonResult(444, "没有记录", null);
        }
    }

    @GetMapping(value = "/feature/getfeaturesbyfield")
    public CommonResult getFeaturesByField(
            @RequestParam(required = false, value = "serviceId") String layerId,
            @RequestParam("column") String column,
            @RequestParam("value") String value
    ) {
        ArrayList<Feature> featureList = new ArrayList<Feature>();
        if (layerId != null) {
            Layer layerData = new Layer();
            layerData = layerService.getLayerByLayerId(layerId);

            String layer = layerData.getLayers() == null ? null : layerData.getLayers().split(":")[1];
            System.out.println(layer + "   layerNamelayerNamelayerName");
            // 应该返回list
            featureList = featureService.getFeaturesByThreeField(layer, column, value);
        } else {
            featureList = featureService.getFeaturesByField(column, value);
        }

        log.info("****查询结果: " + featureList);

        if (featureList != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, featureList);
        } else {
            return new CommonResult(444, "没有记录", null);
        }
    }


}
