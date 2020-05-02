package com.jinshuo.mall.domain.order.product.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @Description: 支付渠道枚举
 * @Author: dongyh
 * @CreateDate: 2019/7/23 16:43
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/7/23 16:43
 * @UpdateRemark:
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PayChannelEnums implements BaseEnum<PayChannelEnums, Integer> {
    /*支付渠道*/
    ORDER_PAY_CHANNEL_CASH(0, "余额"),
    ORDER_PAY_CHANNEL_WECHAT(1, "微信"),
    ORDER_PAY_CHANNEL_ALIPAY(2, "支付宝"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private PayChannelEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PayChannelEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (PayChannelEnums temp : PayChannelEnums.values()) {
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

    @Override
    public Integer getValue() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return desc;
    }

}
