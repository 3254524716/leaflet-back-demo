package com.example.leaflet_back_demo.utils;

/*
 * 解析excel工具类
 * */
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelUtil<T> {

    private T t;

    public ExcelUtil(T t) {
        this.t = t;
    }

    public List<Map> AnalysisExcel(InputStream is, String fileName) throws IOException {

        List<Map> list = new ArrayList<>();
        Workbook workbook = createWorkbookByExcelType(is, fileName); // 创建工作簿

        Sheet sheet = workbook.getSheetAt(0);
        Row row = null;

        int maxRowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int maxColNum = row.getLastCellNum();
        List<String> arrayList = new ArrayList<>();
        Field[] declaredFields = t.getClass().getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            String name = declaredFields[i].getName();
            arrayList.add(name);
        }

        for (int i = 0; i <= maxRowNum; i++) {
            Map<String, String> map = new HashMap<>();
            row = sheet.getRow(i);
            if (row != null){
                for (int j = 0; j < maxColNum; j++) {
                    String cellData = (String)getCellFormatValue(row.getCell(j));
                    map.put(arrayList.get(j), cellData); // map 封装
                }
                list.add(map); // map存入list
            }
        }
        return list;
    }

    public static Workbook createWorkbookByExcelType(InputStream inputStream,String fileName){
        Workbook wb = null;
        if(fileName == null){
            return null;
        }
        String extString = fileName.substring(fileName.lastIndexOf("."));
        InputStream is = null;
        try {
            is = inputStream;
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is); // 2003版本 .xls
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is); // 2007版本 .xlsx
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    // 用于获取表格中的数据方法
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA:{
                    if(DateUtil.isCellDateFormatted(cell)){
                        cellValue = cell.getDateCellValue();
                    }else{
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
}


