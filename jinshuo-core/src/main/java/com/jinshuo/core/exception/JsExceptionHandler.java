package com.jinshuo.core.exception;

import com.jinshuo.core.response.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

/**
 * @Classname JsExceptionHandler
 * @Description TODO
 * @Date 2019/6/20 15:40
 * @Created by dyh
 */
@Slf4j
public abstract class JsExceptionHandler {
    /**
     *  拦截系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({RuntimeException.class,Exception.class})
    @ResponseBody
    WrapperResponse unknowException(Exception e){
        log.error("Exception："+new Date()+"："+e.getMessage(),e);
        return WrapperResponse.fail(JsReturnCode.SYS100099.getCode(), JsReturnCode.SYS100099.getMsg());
    }

    /**
     * 用于处理异常的
     * @return 无服务异常
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    WrapperResponse notFoundException(NoHandlerFoundException e) {
        log.error("ExceptionHandler:"+e.getMessage(),e);
        return WrapperResponse.fail(JsReturnCode.SYS100001.getCode(), JsReturnCode.SYS100001.getMsg());
    }


    /**
     * 拦截YmException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(JsException.class)
    @ResponseBody
    WrapperResponse ymException(JsException e){
        log.error("全局业务异常："+new Date()+"："+e.getErrorMsg(),e);
        return WrapperResponse.fail(e.getRetCode(),e.getRetMsg());
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    WrapperResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.error("参数校验异常:{}({})"+new Date(),fieldError.getDefaultMessage(),fieldError.getField());
        return WrapperResponse.fail(JsReturnCode.SYS100008.getCode(),fieldError.getDefaultMessage());
    }
}
