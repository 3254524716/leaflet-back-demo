package com.example.leaflet_back_demo.service;

import com.example.leaflet_back_demo.entities.Feature;

import java.util.ArrayList;


public interface FeatureService {

    public ArrayList<Feature> getFeaturesByField(String column, String value);

}
