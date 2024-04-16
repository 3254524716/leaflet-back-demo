package com.example.leaflet_back_demo.service.impl;

import com.example.leaflet_back_demo.dao.PayrollDao;
import com.example.leaflet_back_demo.entities.Payroll;
import com.example.leaflet_back_demo.service.PayrollService;
import com.example.leaflet_back_demo.utils.ExcelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PayrollServiceImpl implements PayrollService {
    @Resource
    private PayrollDao payrollDao;

    public List<Map> getListByExcel(InputStream is,String fileName) throws IOException {
        try{
            List<Map> studentList = new ExcelUtil(new Payroll()).AnalysisExcel(is, fileName);
            return studentList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Boolean batchImportPayInfo(List<Map> list){
        Integer flag = payrollDao.batchImportStuInfo(list);
        if (flag > 0){
            return true;
        } else return false;
    }

    //查询所有
    public ArrayList<Payroll> getAllPayroll() {
        return payrollDao.getAllPayroll();
    }
}
