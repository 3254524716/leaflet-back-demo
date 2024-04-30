package com.example.leaflet_back_demo.service.impl;


import com.example.leaflet_back_demo.dao.LayerDao;
import com.example.leaflet_back_demo.entities.Layer;
import com.example.leaflet_back_demo.service.LayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class LayerServiceImpl implements LayerService {

    @Resource
    private LayerDao layerDao;

    @Override
    public ArrayList<Layer> getLayersByCatalogId(Integer catalogId) {
        return layerDao.getLayersByCatalogId(catalogId);
    }
}
