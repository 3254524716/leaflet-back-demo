package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.Layer;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface LayerDao {
    //根据图层所在目录 catalogId 查询图层信息
    ArrayList<Layer> getLayersByCatalogId(Integer catalogId);
}
