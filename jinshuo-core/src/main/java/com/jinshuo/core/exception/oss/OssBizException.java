package com.jinshuo.core.exception.oss;

/**
 * @Classname IcBizException
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by ybh
 */
public class OssBizException extends RuntimeException {
    /**
     * 异常码
     */
    protected int code;
    /**
     * 异常信息
     */
    protected String message;

    private static final long serialVersionUID = 3160241586346324994L;

    public OssBizException() {
    }

    public OssBizException(Throwable cause) {
        super(cause);
    }

    public OssBizException(String message) {
        super(message);
    }

    public OssBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssBizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public OssBizException(int code, String msgFormat, Object... args) {
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
