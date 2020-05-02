package com.jinshuo.core.exception;


/**
 * @Classname JsException
 * @Description 业务异常类
 * @Date 2019/6/17 18:16
 * @Created by dyh
 */
public  class JsException extends RuntimeException {

    private static final long serialVersionUID = -9213841740412778182L;

    /**
     * 错误编码
     */
    private int retCode=000000;

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



    public JsException(String retMsg) {
        super(retMsg);
        this.retMsg = retMsg;
    }
    public JsException(String retMsg, Throwable e) {
        super(retMsg, e);
        this.retMsg = retMsg;
    }
    public JsException(String retMsg, int retCode) {
        super(retMsg);
        this.retMsg = retMsg;
        this.retCode = retCode;
    }

    public JsException(int retCode, String retMsg) {
        super(retMsg);
        this.retMsg = retMsg;
        this.retCode = retCode;
    }

    public JsException(String retMsg, int retCode, Throwable e) {
        super(retMsg, e);
        this.retMsg = retMsg;
        this.retCode = retCode;
    }
    public JsException(String retMsg, int retCode, String errorMsg, int errorCode) {
        super(retMsg);
        this.retMsg = retMsg;
        this.retCode = retCode;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public JsException(String retMsg, int retCode, String errorMsg, int errorCode, Throwable e) {
        super(retMsg, e);
        this.retMsg = retMsg;
        this.retCode = retCode;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }



    public JsException(JsReturnCode resultCode, Throwable e) {
        super(resultCode.getMsg(), e);

        this.errorMsg = resultCode.getMsg();
        this.errorCode = resultCode.getCode();
    }

    public JsException(JsReturnCode resultCode) {
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
