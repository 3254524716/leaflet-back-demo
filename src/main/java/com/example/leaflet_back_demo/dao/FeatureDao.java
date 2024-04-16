package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.Feature;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FeatureDao {
    public ArrayList<Feature> getFeaturesByField(String column, String value);
//    public Feature getFeaturesByField(String column, String value);
}
