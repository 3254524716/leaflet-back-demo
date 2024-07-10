package com.example.leaflet_back_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.leaflet_back_demo.entities.CommonResult;
import com.example.leaflet_back_demo.entities.User;
import com.example.leaflet_back_demo.service.UserService;
import com.example.leaflet_back_demo.utils.TokenUtil;
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


/**
 * 登录
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
    @Resource
    UserService userService;

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
        String code = VertifyCodeUtils.generateVerifyCode(4);
        validate_code = code;
        request.getServletContext().setAttribute("code", code);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VertifyCodeUtils.outputImage(105, 35, byteArrayOutputStream, code);
        String data;
        try {
            data = "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
            data = "";
        }
        if (!data.equals("")) {
            log.info("data: " + data);
        }
        return data;
    }

    // 登录 验证
    @PostMapping("/login")
    public CommonResult loginController(@RequestBody JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject = data;

        //用户名 和密码
        String loginid = jsonObject.getString("loginid");
        String password = jsonObject.getString("password");

        //验证码
        String validate_code_input = jsonObject.getString("captcha");
        //登录时 时间
        String validate_checkKey = jsonObject.getString("checkKey");
        // 对验证码 检验
        JSONObject resultData = new JSONObject();
        //这个验证过程可以 放到service中
        if (validate_code_input.toUpperCase().equals(validate_code)) {
            // 继续验证用户 //密码应该加密存储
            User validateUser =  userService.getUserByLoginId(loginid);
            if(validateUser==null){
                return new CommonResult(442, "用户不存在", null);
            }else if(!validateUser.getPassword().equals(password)){
                return new CommonResult(443, "用户密码错误", null);
            }else if(!validateUser.getActiveflag()){
                return new CommonResult(441, "用户未激活", null);
            }else{
                //登录验证成功
                //生成token签名
                String token = TokenUtil.getToken(validateUser);
                //token 存储到数据库？

                resultData.put("loginid", validateUser.getLoginid());
                resultData.put("userid", validateUser.getUserid());
                resultData.put("token", token);
                return new CommonResult(200, "登录成功", resultData);
            }
        } else { // 验证失败 提示 验证码 出错
            return new CommonResult(444, "验证码错误", null);
        }
    }
}
