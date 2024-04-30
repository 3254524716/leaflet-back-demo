package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.Catalog;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CatalogDao {
    //根据目录父节点id 查询目录
    ArrayList<Catalog> getCatalogsByPid(Integer pid);
    //根据目录等级 查询目录
    ArrayList<Catalog> getCatalogsByLevel(Integer level);
}
