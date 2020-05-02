package com.jinshuo.core.exception.item;

import com.jinshuo.core.exception.ReturnCode;

/**
 * @Classname IcReturnCode
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by dyh
 */
public enum IcReturnCode implements ReturnCode {

    /**
     * IC 000000 error code enum.
     */
    IC200001(200001, "自定义错误1"),

    /**
     * IC 000000 error code enum.
     */
    IC200003(200003, "网络异常，请稍后再试！"),
    /**
     * IC 000000 error code enum.
     */
    IC200002(200002, "自定义错误2"),

    /**
     * IC 202001 error code enum.
     */
    IC201001(201001, "该优惠券已使用！"),

    /**
     * IC 202001 error code enum.
     */
    IC201002(201002, "该优惠券已过期！"),

    /**
     * IC 201003 error code enum.
     */
    IC201003(201003, "该优惠券状态异常！"),

    /**
     * IC 201003 error code enum.
     */
    IC201005(201005, "优惠券不存在！"),
    /**
     * IC 201003 error code enum.
     */
    IC201006(201006, "优惠券已领完！"),

    /**
     * IC 201007 error code enum.
     */
    IC201007(201007, "商品不存在！"),
    /**
     * IC 201007 error code enum.
     */
    IC201008(201008, "库存不足！"),

    /**
     * IC 201009 error code enum.
     */
    IC201009(201009, "参数异常！"),
    /**
     * IC 201010 error code enum.
     */
    IC201010(201010, "该产品不可使用此优惠券！"),
    /**
     * IC 201011 error code enum.
     */
    IC201011(201011, "该优惠券已经超过使用次数！"),
    /**
     * IC 201012 error code enum.
     */
    IC201012(201012, "该优惠券尚未达到满减金额！"),

    /**
     * IC 201012 error code enum.
     */
    IC201013(201013, "优惠券有效期字段不能为空！"),
    /**
     * IC 201012 error code enum.
     */
    IC201014(201014, "请重新登录再继续下一步！"),

    /**
     * IC 201012 error code enum.
     */
    IC201015(201015, "网络异常！"),

    /**
     * IC 201012 error code enum.
     */
    IC201016(201016, "广告位代码已经存在！"),

    /**
     * IC 201012 error code enum.
     */
    IC201017(201017, "活动代码已经存在！"),

    /**
     * IC 201003 error code enum.
     */
    IC201004(201004, "优惠券已过有效期！"),
    /**
     * IC 201003 error code enum.
     */
    IC201018(201018, "优惠卷不可以重复领取！"),
    /**
     * IC 201003 error code enum.
     */
    IC201019(201019, "请选择客户！"),

    /**
     * IC 201003 error code enum.
     */
    IC201020(201020, "该商品已下架！"),
    /**
     * IC 201003 error code enum.
     */
    IC201021(201021, "该商品已结束抢购！"),
    /**
     * IC 201003 error code enum.
     */
    IC201022(201022, "该商品尚未开始抢购！"),
    /**
     * IC 201003 error code enum.
     */
    IC201023(201023, "该商品已经售罄！"),
    /**
     * IC 201003 error code enum.
     */
    IC201024(201024, "该商品状态异常！"),
    /**
     * IC 201003 error code enum.
     */
    IC201025(201025, "广告地区不能为空！");

    private int code;
    private String msg;


    IcReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static IcReturnCode getEnum(int code) {
        for (IcReturnCode ele : IcReturnCode.values()) {
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
