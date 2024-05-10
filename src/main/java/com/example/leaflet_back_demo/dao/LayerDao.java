package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.Layer;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.UUID;

@Mapper
public interface LayerDao {
    //根据图层所在目录 catalogId 查询该目录下图层信息
    ArrayList<Layer> getLayersByCatalogId(Integer catalogId);

    //通过图层id 查询图层信息 (主要是获取图层名 找到对应的要素表)
    Layer getLayerByLayerId(String layerId);
}
