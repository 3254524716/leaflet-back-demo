package com.example.leaflet_back_demo.service.impl;


import com.example.leaflet_back_demo.dao.CatalogDao;
import com.example.leaflet_back_demo.entities.Catalog;
import com.example.leaflet_back_demo.service.CatalogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Resource
    private CatalogDao catalogDao;

    @Override
    public ArrayList<Catalog> getCatalog(Integer pid) {
        return catalogDao.getCatalogsByPid(pid);
    }

    @Override
    public ArrayList<Catalog> getLevelCatalog(Integer level) {
        return catalogDao.getCatalogsByLevel(level);
    }
}
