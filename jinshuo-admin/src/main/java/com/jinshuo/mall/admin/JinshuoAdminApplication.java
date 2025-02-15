package com.jinshuo.mall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.jinshuo.mall.admin","com.jinshuo.mall.service",
        "com.jinshuo.mall.service.order.mybatis.mapper",
        "com.jinshuo.mall.front"})
@MapperScan(basePackages = {"com.jinshuo.mall"})
public class JinshuoAdminApplication {

    public static void main(String[] args) {
        System.out.println(" -- 开始启动金硕果园！-- ");
        SpringApplication.run(JinshuoAdminApplication.class, args);
        System.out.println(" -- 金硕果园启动完成！-- ");
    }

}
