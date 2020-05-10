package com.jinshuo.mall.admin.config.security.auth;

import com.jinshuo.core.constant.AuthParameters;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    /**
     * Generate token for user login.
     *
     * @param authentication
     * @return return a token string.
     */
    public String createJwtToken(Authentication authentication) {
        //user name
        String username = ((org.springframework.security.core.userdetails
                .User) authentication.getPrincipal()).getUsername();
        //expire time
        Date expireTime = new Date(System.currentTimeMillis() + AuthParameters.tokenExpiredMs);
        //create token
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireTime)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthParameters.jwtTokenSecret)
                .compact();
        System.out.println("创建的json为：" + token);
        return token;
    }

    /**
     * validate token eligible.
     * if Jwts can parse the token string and no throw any exception, then the token is eligible.
     *
     * @param token a jws string.
     */
    public boolean validateToken(String token) {
        String VALIDATE_FAILED = "validate failed : ";
        //ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException
        try {
            Jwts.parser().setSigningKey(AuthParameters.jwtTokenSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            logger.error(VALIDATE_FAILED + ex.getMessage());
            return false;
        }
    }

    /**
     * Generate token for user login.
     *
     * @param username
     * @return return a token string.
     */
    public String createJwtToken(String username) {
        Date expireTime = new Date(System.currentTimeMillis() + AuthParameters.tokenExpiredMs);
        //create token
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireTime)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthParameters.jwtTokenSecret)
                .compact();
        System.out.println("创建的json为：" + token);
        return token;
    }
}
