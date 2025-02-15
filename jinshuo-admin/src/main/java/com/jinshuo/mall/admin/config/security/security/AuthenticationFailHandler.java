package com.jinshuo.mall.admin.config.security.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shuangyao
 * 22:50 2018/9/2
 */
@Service("authenticationFailHandler")
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        this.returnJson(response,exception);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    }

    private void returnJson(HttpServletResponse response,
                            AuthenticationException exception) throws IOException {
        System.out.println("--**-- 访问失败！！----");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println("{\"exceptionId\":\"null\",\"messageCode\":\"401\"," +
                "\"message\": \"" + exception.getMessage() + "\",\"serverTime\": " + System.currentTimeMillis() + "}");
    }

    /*@Configuration
    public static class ResourceServerConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("*//**")
     .allowedOrigins("*")
     .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
     .allowCredentials(true).maxAge(36000);
     }
     }*/
}
