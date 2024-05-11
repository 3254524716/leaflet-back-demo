package com.example.leaflet_back_demo.service.impl;

import com.example.leaflet_back_demo.dao.FeatureDao;
import com.example.leaflet_back_demo.entities.Feature;
import com.example.leaflet_back_demo.service.FeatureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class FeatureServiceImpl implements FeatureService {
    @Resource
    private FeatureDao featureDao;

    public ArrayList<Feature> getFeaturesByField(String column, String value){
        return featureDao.getFeaturesByField(column,value);
    }

    public ArrayList<Feature> getFeaturesByThreeField(String layer, String column, String value){
        return featureDao.getFeaturesByThreeField(layer,column,value);
    }

    public ArrayList<Feature> getFeatureBySpace(String polygon){
        String wktPolygon = featureDao.convertGeoJSONToWKT(polygon);
        return featureDao.getFeatureBySpace(wktPolygon);
    }

    public ArrayList<Feature> getFeatureBySpaceAndLayer(String layer, String polygon){
        String wktPolygon = featureDao.convertGeoJSONToWKT(polygon);
        return featureDao.getFeatureBySpaceAndLayer(layer,wktPolygon);
    }
}
