package com.jinshuo.core.exception.sc;

import com.jinshuo.core.exception.JsExceptionHandler;
import com.jinshuo.core.response.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @Classname FcExceptionHandler
 * @Description TODO
 * @Date 2019/6/18 17:24
 * @Created by dyh
 */
@Slf4j
@ControllerAdvice
public class ScExceptionHandler extends JsExceptionHandler {
    /**
     * 拦截OcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(ScBizException.class)
    @ResponseBody
    WrapperResponse ymException(ScBizException e) {
        log.error("订单业务异常：" + new Date() + "：" + e.getMessage(), e);
        return WrapperResponse.fail(e.getCode(), e.getMessage());
    }
}
