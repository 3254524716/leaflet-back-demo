package com.example.leaflet_back_demo.service;

import com.example.leaflet_back_demo.entities.Feature;

import java.util.ArrayList;


public interface FeatureService {

    ArrayList<Feature> getFeaturesByField(String column, String value);

    ArrayList<Feature> getFeaturesByThreeField(String layer, String column, String value);
//    String convertGeoJSONToWKT(String GeojsonPolygon);
    ArrayList<Feature> getFeatureBySpace(String polygon);

    ArrayList<Feature> getFeatureBySpaceAndLayer(String layer, String polygon);
}
