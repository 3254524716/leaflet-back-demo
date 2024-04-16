package com.example.leaflet_back_demo.controller;

import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.Payroll;
import com.example.leaflet_back_demo.service.PayrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PayrollController {
    @Resource
    private PayrollService payrollService;

    @Value("${server.port}")
    private String serverPort;

    //处理上传的文件
    @PostMapping(value = "/payroll/upload")
    public CommonResult create(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename(); // 获取文件名
        InputStream is = null;
        try{
            is = file.getInputStream();
            List<Map> payrollList = payrollService.getListByExcel(is,fileName);// 获取解析后的List集合
            // System.out.println(studentList.toString());
            Boolean result = payrollService.batchImportPayInfo(payrollList); // 把数据插入数据库
            if (result){
                log.info("****插入结果: " + result);
                return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
            }else {
                log.info("****插入结果: " + result);
                return new CommonResult(444, "插入数据库失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            is.close();
        }
        return new CommonResult(401,"info", "文件错误！");

    }


    //查询所有
    @GetMapping(value = "/payroll/getAllPayroll")
    public CommonResult getAllPayroll() {
        // 应该返回list
        ArrayList<Payroll> payrollList = payrollService.getAllPayroll();
        log.info("****查询结果: " + payrollList);

        if (payrollList != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payrollList);
        } else {
            return new CommonResult(444, "没有记录" , null);
        }
    }

}
