package com.jinshuo.mall.domain.order.product.orderrefund;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;
import com.jinshuo.mall.domain.order.product.order.SettlementStatusEnums;

/**
 * @Description: 退单状态枚举
 * @Author: dongyh
 * @CreateDate: 2019/12/23 16:43
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderRefundStatusEnums implements BaseEnum<OrderRefundStatusEnums, Integer> {
    ORDER_STATUS_REVIEWING(1, "待审核"),
    ORDER_STATUS_RETURNED(2, "待收货"),
    ORDER_STATUS_PAYING(3, "待打款"),
    ORDER_STATUS_COMPLETE(0, "已完成"),
    ORDER_STATUS_REFUSE(4, "已驳回"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private OrderRefundStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderRefundStatusEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (OrderRefundStatusEnums temp : OrderRefundStatusEnums.values()) {
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
