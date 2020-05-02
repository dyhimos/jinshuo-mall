package com.jinshuo.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Classname SysLog
 * @Description 日志切面注解      例如：@SysLog(value="请求了aopDemo方法") @SysLog(ignore=true)
 * @Date 2019/6/23 23:56
 * @Created by dyh
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})//只能在方法上使用此注解
public @interface SysLog {
    /**
     * 日志描述，这里使用了@AliasFor 别名。spring提供的
     * @return
     */
    @AliasFor("desc")
    String value() default "";

    /**
     * 日志描述
     * @return
     */
    @AliasFor("value")
    String desc() default "";

    /**
     * 是否不记录日志
     * @return
     */
    boolean ignore() default false;
}