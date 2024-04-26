package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.Catalog;
import com.example.leaflet_back_demo.entities.Feature;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CatalogDao {
    ArrayList<Catalog> getCatalogsByPid(Integer pid);
}
