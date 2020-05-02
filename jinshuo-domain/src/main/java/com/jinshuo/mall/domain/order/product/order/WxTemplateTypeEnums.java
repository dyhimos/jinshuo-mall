package com.jinshuo.mall.domain.order.product.order;

/**
 * @Description: 微信消息模板
 * @Author: dongyh
 * @CreateDate: 2019/7/23 16:43
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/7/23 16:43
 * @UpdateRemark:
 * @Version: 1.0
 */
public enum WxTemplateTypeEnums {
    WX_PAY_SUCCESS_TEMPLATE(1, "支付成功"),
    WX_EXPRESS_SUCCESS_TEMPLATE(2, "发货成功"),
    WX_VERIFICATION_SUCCESS_TEMPLATE(3, "发码成功"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private WxTemplateTypeEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static WxTemplateTypeEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (WxTemplateTypeEnums temp : WxTemplateTypeEnums.values()) {
            if (temp.getCode().equals(code)) {
                return temp;
            }
        }
        return null;
    }


    public static String getDescByCode(Integer code) {
        if (code == null) {
            return "";
        }
        for (SettlementStatusEnums temp : SettlementStatusEnums.values()) {
            if (temp.getCode().equals(code)) {
                return temp.getDesc();
            }
        }
        return "";
    }
}
