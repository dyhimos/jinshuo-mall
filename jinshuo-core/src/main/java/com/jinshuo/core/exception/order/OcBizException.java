package com.jinshuo.core.exception.order;

import com.jinshuo.core.exception.JsReturnCode;

/**
 * @Classname OcBizException
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by dongyh
 */
public class OcBizException extends RuntimeException {
    private static final long serialVersionUID = -9213841740412778182L;

    /**
     * 错误编码
     */
    private int retCode = 000000;

    /**
     * 错误消息
     */
    private String retMsg;

    /**
     * 描述用于展示
     */
    private String errorMsg;

    /**
     * errorCode 表示后台系统返回错误码
     */
    private int errorCode = 000000;


    public OcBizException(String retMsg) {
        super(retMsg);
        this.retMsg = retMsg;
    }

    public OcBizException(String retMsg, Throwable e) {
        super(retMsg, e);
        this.retMsg = retMsg;
    }

    public OcBizException(String retMsg, int retCode) {
        super(retMsg);
        this.retMsg = retMsg;
        this.retCode = retCode;
    }

    public OcBizException(String retMsg, int retCode, Throwable e) {
        super(retMsg, e);
        this.retMsg = retMsg;
        this.retCode = retCode;
    }

    public OcBizException(String retMsg, int retCode, String errorMsg, int errorCode) {
        super(retMsg);
        this.retMsg = retMsg;
        this.retCode = retCode;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public OcBizException(String retMsg, int retCode, String errorMsg, int errorCode, Throwable e) {
        super(retMsg, e);
        this.retMsg = retMsg;
        this.retCode = retCode;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }


    public OcBizException(JsReturnCode resultCode, Throwable e) {
        super(resultCode.getMsg(), e);

        this.errorMsg = resultCode.getMsg();
        this.errorCode = resultCode.getCode();
    }

    public OcBizException(JsReturnCode resultCode) {
        this.errorMsg = resultCode.getMsg();
        this.errorCode = resultCode.getCode();
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
