package com.example.leaflet_back_demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.leaflet_back_demo.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//应该是 请求的token验证
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");

//         前端请求 token位置
        String token = request.getHeader("Authorization");

//        Cookie[] cookies = request.getCookies();
//        String token = null;
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("Authorization".equals(cookie.getName())) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        if (token != null) {
            Integer result = TokenUtil.verify(token);
            if (result == 200) {
                System.out.println("通过拦截器");
                return true;
            } else if(result == 50014) {
                try {
                    JSONObject json = new JSONObject();
                    json.put("msg", "登录已过期 请重新登录");
                    json.put("code", "50018");
                    response.getWriter().append(json.toJSONString());
                    response.setStatus(50014);
                    System.out.println("登录已过期 请重新登录");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(50014);
                }
                return false;
            }else{
                try {
                    JSONObject json = new JSONObject();
                    json.put("msg", "认证失败 无效token");
                    json.put("code", "50008");
                    response.getWriter().append(json.toJSONString());
                    response.setStatus(50008);
                    System.out.println("认证失败 无效token 50008");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(50008);
                }
                return false;
            }
        } else {
            // 请求携带的token为空的情况
            try {
                JSONObject json = new JSONObject();
                json.put("msg", "认证失败 无效token");
                json.put("code", "50000");
                response.getWriter().append(json.toJSONString());
                response.setStatus(50008);
                System.out.println("认证失败，未通过拦截器");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(50008);
            }
            return false;
        }
    }
}

