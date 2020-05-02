package com.jinshuo.core.exception;

/**
 * @Classname YmReturnCode
 * @Description TODO
 * @Date 2019/6/20 17:36
 * @Created by dyh
 */
public enum YmReturnCode implements ReturnCode{

    /**
     * SYS 000000 error code enum.
     */
    SYS000000(000000, "操作成功"),

    /**
     * SYS SYS000001 error code enum.
     */
    SYS000001(-1, "操作失败"),


    /**
     * SYS 100000 error code enum.
     */
    SYS100000(100000, "服务器内部错误"),
    /**
     * Gl 100001 error code enum.
     */
    SYS100001(100001, "网络错误"),
    /**
     * SYS 100002 error code enum.
     */
    SYSS100002(100002, "目标服务器错误"),
    /**
     * SYS 100003 error code enum.
     */
    SYS100003(100003, "用户帐号不存在"),
    /**
     * SYS 100004 error code enum.
     */
    SYS100004(100004, "您的账户已停用"),
    /**
     * SYS 100005 error code enum.
     */
    SYS100005(100005, "密码错误"),
    /**
     * SYS 100006 error code enum.
     */
    SYS100006(100006, "您没有请求权限"),
    /**
     * SYS 100007 error code enum.
     */
    SYS100007(100007, "接口不存在"),
    /**
     * SYS 100008 error code enum.
     */
    SYS100008(100008, "输入参数错误"),
    /**
     * SYS 100009 error code enum.
     */
    SYS100009(100009, "访问频率过高"),
    /**
     * SYS 100010 error code enum.
     */
    SYS100010(100010, "访问次数达到限制"),
    /**
     * SYS 100011 error code enum.
     */
    SYS100011(100011, "被禁止的IP"),
    /**
     * SYS 100099 error code enum.
     */
    SYS100099(100099, "未知异常")
    ;



    private  int code;
    private  String msg;


    YmReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static YmReturnCode getEnum(int code) {
        for (YmReturnCode ele : YmReturnCode.values()) {
            if (ele.getCode()== code) {
                return ele;
            }
        }
        return null;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
