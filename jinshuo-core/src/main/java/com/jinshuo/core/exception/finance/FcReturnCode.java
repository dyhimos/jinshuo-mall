package com.jinshuo.core.exception.finance;

import com.jinshuo.core.exception.ReturnCode;

/**
 * @Classname IcReturnCode
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by dongyh
 */
public enum FcReturnCode implements ReturnCode {

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
    IC201001(201001, "该优惠券已使用！"),

    /**
     * IC 202001 error code enum.
     */
    IC201002(206002, "该优惠券已过期！"),

    /**
     * IC 201003 error code enum.
     */
    IC206001(206001, "请登录再进行下一步！"),

    /**
     * IC 201003 error code enum.
     */
    IC206002(206002, "根据订单信息查询上级分销员失败！"),

    /**
     * IC 201003 error code enum.
     */
    IC206003(206003, "根据订单信息查询产品提佣比例失败！"),

    /**
     * IC 206004 error code enum.
     */
    IC206004(206004, "结算类型不能为空！"),
    /**
     * IC 206005 error code enum.
     */
    IC206005(206005, "提款金额大于可提现金额！"),

    /**
     * IC 206006 error code enum.
     */
    IC206006(206006, "完成提现金额大于提现中现金额！"),

    /**
     * IC 206007 error code enum.
     */
    IC206007(206007, "完成金额大于待结算金额！"),

    /**
     * IC 206008 error code enum.
     */
    IC206008(206008, "申请提现金额不能为空！"),

    /**
     * IC 206009 error code enum.
     */
    IC206009(206009, "网络异常，请稍后再试！"),
    /**
     * IC 201004 error code enum.
     */
    IC201004(201004, "优惠券已过有效期！"),

    /**
     * IC 206010 error code enum.
     */
    IC206010(206010, "向分销系统增加订单金额失败！"),

    /**
     * IC 206011 error code enum.
     */
    IC206011(206011, "该订单不存在佣金记录！"),
    /**
     * IC 206012 error code enum.
     */
    IC206012(206012, "该订单佣金已结算！"),
    /**
     * IC 206013 error code enum.
     */
    IC206013(206013, "提现申请记录查询失败！"),

    /**
     * IC 206014 error code enum.
     */
    IC206014(206014, "获取分销员信息失败！"),

    /**
     * IC 206015 error code enum.
     */
    IC206015(206015, "提现记录不存在！"),
    /**
     * IC 206015 error code enum.
     */
    IC206016(206016, "提现金额小于最小提现金额！"),
    /**
     * IC 206015 error code enum.
     */
    IC206017(206017, "麦客信息不全，请先补全麦客信息！"),

    /**
     * IC 206015 error code enum.
     */
    IC206018(206018, "财务账户已停用！"),
    /**
     * IC 206015 error code enum.
     */
    IC206019(206019, "充值金额不能小于0！"),
    /**
     * IC 206015 error code enum.
     */
    IC206020(206020, "余额不足！"),
    /**
     * IC 206015 error code enum.
     */
    IC206021(206021, "账户已存在！"),
    /**
     * IC 206015 error code enum.
     */
    IC206022(206022, "账户不存在！"),

    /**
     * 账户已停用
     */
    IC206023(206023, "账户已停用！"),
    ;

    private int code;
    private String msg;


    FcReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static FcReturnCode getEnum(int code) {
        for (FcReturnCode ele : FcReturnCode.values()) {
            if (ele.getCode() == code) {
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
