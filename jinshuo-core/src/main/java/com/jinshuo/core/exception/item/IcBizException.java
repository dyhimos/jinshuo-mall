package com.jinshuo.core.exception.item;

/**
 * @Classname IcBizException
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by dyh
 */
public class IcBizException extends RuntimeException {
    /**
     * 异常码
     */
    protected int code;
    /**
     * 异常信息
     */
    protected String message;

    private static final long serialVersionUID = 3160241586346324994L;

    public IcBizException() {
    }

    public IcBizException(Throwable cause) {
        super(cause);
    }

    public IcBizException(String message) {
        super(message);
    }

    public IcBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public IcBizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public IcBizException(int code, String msgFormat, Object... args) {
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
