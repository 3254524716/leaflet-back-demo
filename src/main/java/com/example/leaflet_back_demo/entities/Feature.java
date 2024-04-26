package com.example.leaflet_back_demo.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//要素类 用来解决要素相关查询等问题
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    private Integer gid;        //数据库id
    @JsonProperty("FeatureID")
    private Integer FeatureID;  //要素id
    @JsonProperty("DKBH")
    private String DKBH;        //地块编号
    @JsonProperty("YDMJ")
    private Float YDMJ;         //用地面积
    @JsonProperty("GHYT")
    private String GHYT;        //规划用途
    @JsonProperty("YTDM")
    private String YTDM;        //用途代码
    @JsonProperty("HHBL")
    private String HHBL;        //混合比例
    @JsonProperty("JFBH")
    private String JFBH;        //什么编号？
    @JsonProperty("PTSS")
    private String PTSS;        //配套设施
    @JsonProperty("JZGD")
    private Float JZGD;         //建筑高度
    @JsonProperty("RJL")
    private Float RJL;          //容积率
    @JsonProperty("JZMJ")
    private Float JZMJ;         //建筑面积
    @JsonProperty("JZMD")
    private Float JZMD;         //建筑密度
    @JsonProperty("LDL")
    private Float LDL;          //绿地率
    @JsonProperty("TCWS")
    private Integer TCWS;       //停车位数
    @JsonProperty("Remark")
    private String Remark;      //备注
    @JsonProperty("SSGH")
    private Integer SSGH;       //所属规划
    @JsonProperty("DKMJ")
    private Float DKMJ;         //地块面积
    @JsonProperty("JDCCR")
    private String JDCCR;       //朝向？
    @JsonProperty("JSDT")
    private String JSDT;        //建设状态？
    @JsonProperty("geometry")
    private String geometry;        //geometry
}
