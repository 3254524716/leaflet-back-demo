package com.example.leaflet_back_demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.leaflet_back_demo.entities.User;
import cn.hutool.core.date.DateUtil;
import java.util.Date;


public class TokenUtil {

    //过期时间 2小时
    private static final Integer EXPIRE_TIME = 2;
    private static final String TOKEN_SECRET = "qpnz";  //密钥盐

    /**
     * 签名生成
     *
     * @param user
     * @return
     */
    public static String getToken(User user) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("loginid", user.getLoginid())
                    .withExpiresAt(DateUtil.offsetHour(new Date(),EXPIRE_TIME))
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     *
     * @param token
     * @return code  token无效:50008   token过期:50014
     */
    public static Integer verify(String token) {
        String validateToken = null;
        if (token.startsWith("Bearer ")) {
            validateToken = token.substring(7);
        }else{
            validateToken = token;
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(validateToken);
            System.out.println("认证通过: ");
            System.out.println("loginid: " + jwt.getClaim("loginid").asString());
            System.out.println("过期时间: " + jwt.getExpiresAt());
            return 200;
        } catch (TokenExpiredException e) {
            System.out.println("认证失败: token已过期 50014");
            return 50014;
        } catch (Exception e) {
            System.out.println("认证失败: token无效 50008");
            return 50008;
        }
    }
}
