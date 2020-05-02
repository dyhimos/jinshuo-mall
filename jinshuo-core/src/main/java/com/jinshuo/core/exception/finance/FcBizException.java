package com.jinshuo.core.exception.finance;

/**
 * @Classname IcBizException
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by dongyh
 */
public class FcBizException extends RuntimeException {
    /**
     * 异常码
     */
    protected int code;
    /**
     * 异常信息
     */
    protected String message;

    private static final long serialVersionUID = 3160241586346324994L;

    public FcBizException() {
    }

    public FcBizException(Throwable cause) {
        super(cause);
    }

    public FcBizException(String message) {
        super(message);
    }

    public FcBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public FcBizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public FcBizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BizException [message=" + message + ", code=" + code + "]";
    }

}
