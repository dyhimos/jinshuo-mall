package com.jinshuo.core.exception.oss;


import com.jinshuo.core.exception.ReturnCode;

/**
 * @Classname IcReturnCode
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by ybh
 */
public enum OssReturnCode implements ReturnCode {

    /**
     * IC 000000 error code enum.
     */
    IC200001(200001, "自定义错误1"),
    /**
     * IC 000000 error code enum.
     */
    IC200002(200002, "网络异常！"),

    /**
     * IC 202001 error code enum.
     */
    IC210001(210001, "参数异常！"),
    /**
     * IC 202001 error code enum.
     */
    IC210002(209002, "话题已经审核，不允许修改！"),
    /**
     * IC 202001 error code enum.
     */
    IC210003(209003, "请登录后再点击下一步！"),
    ;

    private int code;
    private String msg;


    OssReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OssReturnCode getEnum(int code) {
        for (OssReturnCode ele : OssReturnCode.values()) {
            if (ele.getCode() == code) {
                return ele;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
