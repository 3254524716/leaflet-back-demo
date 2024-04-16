package com.example.leaflet_back_demo.service;

import com.example.leaflet_back_demo.entities.Payroll;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PayrollService {
    public List<Map> getListByExcel(InputStream is, String fileName) throws IOException;
    public Boolean batchImportPayInfo(List<Map> list);
    //查询所有
    public ArrayList<Payroll> getAllPayroll();
}
