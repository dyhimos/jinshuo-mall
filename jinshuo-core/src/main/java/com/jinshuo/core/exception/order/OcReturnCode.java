package com.jinshuo.core.exception.order;

/**
 * @Classname IcReturnCode
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by dongyh
 */
public enum OcReturnCode {

    OC202001(202001, "优惠券检验错误！"),
    OC202002(202002, "网络异常，请稍后再试！"),
    OC202003(202003, "当前订单不存在物流信息！"),
    OC202004(202004, "卷码不存在！"),
    OC202005(202005, "该商品订单不存在！"),
    OC202006(202006, "卷码非该商家！"),
    OC202007(202007, "卷码已核销！"),
    OC202008(202008, "请登录后再试！"),
    OC202009(202009, "订单已发货，请签收后再发起退款！"),
    OC202010(202010, "请勿重复发起退款！"),
    OC202011(202011, "订单已支付，无需重复支付！"),
    ;
    private int code;
    private String msg;


    OcReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OcReturnCode getEnum(int code) {
        for (OcReturnCode ele : OcReturnCode.values()) {
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
