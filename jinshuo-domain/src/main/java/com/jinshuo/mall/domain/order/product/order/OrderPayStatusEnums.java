package com.jinshuo.mall.domain.order.product.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @Description: 订单支付状态枚举
 * @Author: dyh
 * @CreateDate: 2019/12/16 16:43
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderPayStatusEnums implements BaseEnum<OrderPayStatusEnums, Integer> {
    ORDER_PAY_STATUS_PAY(0, "已付款"),
    ORDER_PAY_STATUS_UNPAY(1, "未付款"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private OrderPayStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderPayStatusEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (OrderPayStatusEnums temp : OrderPayStatusEnums.values()) {
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
