package com.example.leaflet_back_demo.dao;

import com.example.leaflet_back_demo.entities.Payroll;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface PayrollDao {
    // 批量导入信息
    public Integer batchImportStuInfo(List<Map> payroll);
    //查询所有
    public ArrayList<Payroll> getAllPayroll();
}
