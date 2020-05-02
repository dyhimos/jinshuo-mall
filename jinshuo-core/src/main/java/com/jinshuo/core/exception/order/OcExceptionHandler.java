package com.jinshuo.core.exception.order;

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
 * @Created by dongyh
 */
@Slf4j
@ControllerAdvice
public class OcExceptionHandler extends JsExceptionHandler {
    /**
     * 拦截OCBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(OcBizException.class)
    @ResponseBody
    WrapperResponse ymException(OcBizException e){
        log.error("订单业务异常："+new Date()+"："+e.getErrorMsg(),e);
        return WrapperResponse.fail(e.getRetCode(),e.getRetMsg());
    }
}
