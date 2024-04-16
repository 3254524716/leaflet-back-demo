package com.example.leaflet_back_demo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//要素类 用来解决查询等问题
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    private Integer gid;        //数据库id
    private Integer featureid;  //要素id
    private String dkbh;        //地块编号
    private Float ydmj;         //用地面积
    private String ghyt;        //规划用途
    private String ytdm;        //用途代码
    private String hhbl;        //混合比例
    private String jfbh;        //什么编号？
    private String ptss;        //配套设施
    private Float jzgd;         //建筑高度
    private Float rjl;          //容积率
    private Float jzmj;         //建筑面积
    private Float jzmd;         //建筑密度
    private Float ldl;          //绿地率
    private Integer tcws;       //停车位数
    private String remark;      //备注
    private Integer ssgh;       //所属规划
    private Float dkmj;         //地块面积
    private String jdccr;       //朝向？
    private String jsdt;        //建设状态？
    private String geom;        //geometry
}
