package com.example.leaflet_back_demo.service;

import com.example.leaflet_back_demo.entities.Catalog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface CatalogService {
    //这个服务不需要参数 调用dao时给定参数 pid=0
    ArrayList<Catalog> getCatalog(Integer pid);
    ArrayList<Catalog> getLevelCatalog(Integer pid);
}
