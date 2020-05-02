package com.jinshuo.core.exception.item;

import com.jinshuo.core.exception.JsExceptionHandler;
import com.jinshuo.core.response.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Classname GlobalExceptionHandler
 * @Description TODO
 * @Date 2019/6/18 17:24
 * @Created by dyh
 */

@Slf4j
@ControllerAdvice
public class IcExceptionHandler extends JsExceptionHandler {
    /**
     * 拦截UcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(IcBizException.class)
    @ResponseBody
    WrapperResponse ymException(IcBizException e){
        log.error("用户业务异常："+new Date()+"："+e.getMessage(),e);
        return WrapperResponse.fail(e.getCode(),e.getMessage());
    }

}
