package com.jinshuo.mall.admin.config.security.security;

import com.jinshuo.mall.admin.config.security.auth.JwtAuthenticationFilter;
import com.jinshuo.mall.admin.config.security.userService.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Configuration
    @ComponentScan({"com.jinshuo.mall.admin.config.security.auth", "com.jinshuo.mall.admin.config.security.security", "com.jinshuo.mall.admin.config.security.userService"})
    public static class MySecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserLoginService userDetailsService;

        @Autowired
        @Qualifier("authenticationSuccessHandler")
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        @Qualifier("authenticationFailHandler")
        private AuthenticationFailHandler failHandler;

        @Autowired
        @Qualifier("authenticationEntryPointImpl")
        private AuthenticationEntryPoint entryPoint;

        @Bean
        public JwtAuthenticationFilter getJwtAuthenticationFilter() {
            return new JwtAuthenticationFilter();
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http.addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers(
                            "/v1/api/manager/login",
                            "/v1/manager/ad/**",
                            "/v1/api/user/login",
                            "/v2/**",
                            "/swagger-ui.html",
                            "/swagger-resources/**",
                            "/webjars/**").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().loginProcessingUrl("/api/login1").usernameParameter("username").passwordParameter("password").permitAll()
                    .successHandler(successHandler)
                    .failureHandler(failHandler)
                    .and().exceptionHandling().authenticationEntryPoint(entryPoint);
        }

        /*@Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            System.out.println("--**-- dsjhfjdsfhsjkf" + auth.toString());
            auth.userDetailsService(userDetailsService).passwordEncoder(new CustomPasswordEncoder());
        }*/
    }
}
