package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.Feature;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FeatureDao {
    ArrayList<Feature> getFeaturesByField(String column, String value);
//    public Feature getFeaturesByField(String column, String value);

    ArrayList<Feature> getFeaturesByThreeField(String layer, String column, String value);

    String convertGeoJSONToWKT(String GeojsonPolygon);

    ArrayList<Feature> getFeatureBySpace(String polygon);
    ArrayList<Feature> getFeatureBySpaceAndLayer(String layer, String polygon);
}
