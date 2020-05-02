package com.jinshuo.core.utils;

import com.jinshuo.core.response.WrapperResponse;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

/**
 * @author dyh
 * @Title: JwtTokenUtils
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/8/2 11:31
 */
public class JwtTokenUtils {
    /**
     * 生成token
     *
     * @return
     */
    public static String createToken(Map<String, Object> claims,String secret,int expire) {

        //设置过期时间
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        String compactJws = Jwts.builder()
                .setIssuedAt(nowDate)
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .compressWith(CompressionCodecs.DEFLATE)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return compactJws;
    }

    /**
     * token解析
     */
    public static Claims getClaim(String token,String secret) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAt(String token,String secret) {
        return getClaim(token,secret).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String token,String secret) {
        return getClaim(token,secret).getExpiration();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String token,String secret) {
        try {
            final Date expiration = getExpiration(token,secret);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }


    /**
     * 验证jwt
     * @param jwtStr
     * @return
     */
    public static boolean validateJWT(String jwtStr,String secret) {
        WrapperResponse wrapperResponse = new WrapperResponse();
        Claims claims = null;
        try {
            claims = getClaim(jwtStr,secret);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (SignatureException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
