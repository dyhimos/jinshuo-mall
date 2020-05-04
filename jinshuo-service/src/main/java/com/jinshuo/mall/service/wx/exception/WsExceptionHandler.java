package com.jinshuo.mall.service.wx.exception;

import com.jinshuo.core.exception.JsExceptionHandler;
import com.jinshuo.core.response.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @Classname YmExceptionHandler
 * @Description TODO
 * @Date 2019/6/18 17:24
 * @Created by mgh
 */
@Slf4j
@ControllerAdvice
public class WsExceptionHandler extends JsExceptionHandler {
    /**
     * 拦截UcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(WsBizException.class)
    @ResponseBody
    WrapperResponse ymException(WsBizException e){
        log.error("用户业务异常："+new Date()+"："+e.getErrorMsg(),e);
        return WrapperResponse.fail(e.getRetCode(),e.getRetMsg());
    }
}
