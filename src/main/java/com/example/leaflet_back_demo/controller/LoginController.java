package com.example.leaflet_back_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.leaflet_back_demo.utils.VertifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


/**
 * 登录 相关控制器
 */

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:8089/",
        "http://192.168.0.122:8089",
        "http://192.168.0.122:8088",
        "http://localhost:8088"},
        allowCredentials = "true")//跨域设置
public class LoginController {

//    @Resource
//    private LayerService layerService;

    //需要做登录验证
//    @Resource
//    LoginService loginService;

    @Value("${server.port}")
    private String serverPort;


    private String validate_code = null;
    private Date validate_star;
    private Date validate_end;

    // 生成验证码
    @GetMapping("/captcha/{time}")
    public String getImageCode(HttpServletRequest request, @PathVariable String time) throws IOException {
        System.out.println("time: " + time);
        validate_star = new Date(Long.parseLong(time));
        // 1. 使用工具类生成验证码
        String code = VertifyCodeUtils.generateVerifyCode(4);
        validate_code = code; // 存放生成的验证码
        // 2. 将验证码放入ServletContext作用域
        request.getServletContext().setAttribute("code", code);
        // 3. 将图片转换成base64格式
        // 字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //将得到的验证码，使用工具类生成验证码图片，并放入到字节数组缓存区
        VertifyCodeUtils.outputImage(105, 35, byteArrayOutputStream, code);
        //使用spring提供的工具类，将字节缓存数组中的验证码图片流转换成Base64的形式
        //并返回给浏览器
//        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        String data;
        try {
            data = "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
            data = "";
        }

        if (!data.equals("")) {
            log.info("data: " + data);
            return data;
//            return new CommonResult(200, "查询成功,serverPort:" + serverPort, data);
        } else {
            return data;
//            return new CommonResult(444, "没有记录", null);
        }
    }


    // 登录 验证
    @PostMapping("/login")
    public Map<String, Object> loginController(@RequestBody JSONObject data) {

        //System.out.println(data.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject = data;

        //用户名 和密码
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        //验证码
        String validate_code_input = jsonObject.getString("captcha");
        //登录时 时间
        String validate_checkKey = jsonObject.getString("checkKey");


        // 对验证码 检验
        JSONObject obj = new JSONObject();
        if (validate_code_input.equals(validate_code)) {
            // 继续验证用户
//            return loginService.loginService(user_id,user_pswd);
            obj.put("validate_result", "success");
            System.out.println("validate_result success");
        } else { // 验证失败 提示 验证码 出错
            obj.put("validate_result", "failed");
            System.out.println("validate_result failed");
        }
        return obj;
    }


}
