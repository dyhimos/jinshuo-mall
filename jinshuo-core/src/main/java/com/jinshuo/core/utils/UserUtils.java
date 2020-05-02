package com.jinshuo.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.model.UserAuthDto;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

public class UserUtils {

    private static final String AUTHORIZATION = "Authorization";

    public UserUtils() {
    }

    public static String getCurrentToken() {
        return (String)HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get("Authorization");
    }

    public static Claims getCurrentUserInfo(String secret) {
        System.out.println("===========获取到的头部信息======：" + (String)HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get("Authorization"));
        String token = getCurrentToken().split("Bearer ")[1];
        Claims claims = JwtTokenUtils.getClaim(token, secret);
        return claims;
    }

    public static Long getCurrentFrontUserId(String secret) {
        System.out.println("===========获取到的头部信息======：" + (String)HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get("Authorization"));
        String token = getCurrentToken().split("Bearer ")[1];
        Claims claims = JwtTokenUtils.getClaim(token, secret);
        return Long.valueOf(String.valueOf(claims.get("userId")));
    }

    public static String getCurrentFrontUserName(String secret) {
        String token = getCurrentToken().split("Bearer ")[1];
        Claims claims = JwtTokenUtils.getClaim(token, secret);
        return String.valueOf(claims.get("userName"));
    }

    public static UserAuthDto getMerchantUserInfo() {
        Map user = (Map) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(user));
        UserAuthDto userAuthDto = (UserAuthDto)JSON.toJavaObject(itemJSONObj, UserAuthDto.class);
        return userAuthDto;
    }
}
