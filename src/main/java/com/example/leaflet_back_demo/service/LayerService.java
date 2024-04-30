package com.example.leaflet_back_demo.service;

import com.example.leaflet_back_demo.entities.Layer;
import java.util.ArrayList;


public interface LayerService {
    //
    ArrayList<Layer> getLayersByCatalogId(Integer catalogId);
}
