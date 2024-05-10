package com.example.leaflet_back_demo.service.impl;


import com.example.leaflet_back_demo.dao.LayerDao;
import com.example.leaflet_back_demo.entities.Layer;
import com.example.leaflet_back_demo.service.LayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class LayerServiceImpl implements LayerService {

    @Resource
    private LayerDao layerDao;

    @Override
    public ArrayList<Layer> getLayersByCatalogId(Integer catalogId) {
        return layerDao.getLayersByCatalogId(catalogId);
    }

    @Override
    //通过图层id 查询图层信息 (主要是获取图层名 找到对应的要素表)
    public Layer getLayerByLayerId(String layerId){
        return layerDao.getLayerByLayerId(layerId);
    }
}
