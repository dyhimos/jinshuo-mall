package com.jinshuo.core.exception.oss;

import com.jinshuo.core.exception.JsExceptionHandler;
import com.jinshuo.core.exception.finance.FcBizException;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.exception.sc.ScBizException;
import com.jinshuo.core.exception.user.UcBizException;
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
public class OssExceptionHandler extends JsExceptionHandler {
    /**
     * 拦截OcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(OssBizException.class)
    @ResponseBody
    WrapperResponse ymException(OssBizException e) {
        log.error("上传业务异常：" + new Date() + "：" + e.getMessage(), e);
        return WrapperResponse.fail(e.getCode(), e.getMessage());
    }

    /**
     * 拦截OcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(UcBizException.class)
    @ResponseBody
    WrapperResponse ymException(UcBizException e) {
        log.error("用户业务异常：" + new Date() + "：" + e.getMessage(), e);
        return WrapperResponse.fail(e.getRetCode(), e.getMessage());
    }

    /**
     * 拦截OcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(IcBizException.class)
    @ResponseBody
    WrapperResponse ymException(IcBizException e) {
        log.error("产品业务异常：" + new Date() + "：" + e.getMessage(), e);
        return WrapperResponse.fail(e.getCode(), e.getMessage());
    }

    /**
     * 拦截OcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(FcBizException.class)
    @ResponseBody
    WrapperResponse ymException(FcBizException e) {
        log.error("财务业务异常：" + new Date() + "：" + e.getMessage(), e);
        return WrapperResponse.fail(e.getCode(), e.getMessage());
    }

    /**
     * 拦截ScBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(ScBizException.class)
    @ResponseBody
    WrapperResponse ymScException(ScBizException e) {
        log.error("分销：" + new Date() + "：" + e.getMessage(), e);
        return WrapperResponse.fail(e.getCode(), e.getMessage());
    }

    /**
     * 拦截OcBizException业务的异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(OcBizException.class)
    @ResponseBody
    WrapperResponse ymException(OcBizException e) {
        log.error("订单业务异常：" + new Date() + "：" + e.getMessage(), e);
        return WrapperResponse.fail(e.getRetCode(), e.getMessage());
    }
}
