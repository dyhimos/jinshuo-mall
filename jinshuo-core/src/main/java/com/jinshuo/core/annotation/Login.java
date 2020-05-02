package com.jinshuo.core.annotation;

import java.lang.annotation.*;

/**
 * @Classname Login
 * @Description 登录效验
 * @Date 2019/6/25 10:35
 * @Created by dyh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
