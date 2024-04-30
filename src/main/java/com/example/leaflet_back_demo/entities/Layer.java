package com.example.leaflet_back_demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Layer {
    private UUID id;            //图层id
    private String name;        //图层名
    private String description; //描述
    private String layers;      //图层名 (请求时用的 图层名)
    private String url;         //地址 (放从哪请求)
    @JsonProperty("sourceBbox")
    private String sourceBbox;  //该图层框
    private String crs;         //该图层坐标系
    @JsonProperty("dataCatagory")
    private String dataCatagory;
    @JsonProperty("catalogId")
    private Integer[] catalogId; //该图层所在 图层目录
}
