package com.jinshuo.mall.domain.order.product.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @Description: 订单状态枚举
 * @Author: dongyh
 * @CreateDate: 2019/7/23 16:43
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/7/23 16:43
 * @UpdateRemark:
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatusEnums implements BaseEnum<OrderStatusEnums, Integer> {
    ORDER_STATUS_UNPAY(0, "待付款"),
    ORDER_STATUS_PAY(1, "待发货"),
    ORDER_STATUS_SHIPPED(2, "待收货"),
    ORDER_STATUS_SIGNED(3, "已签收"),
    ORDER_STATUS_REFUNDAPPLY(-1, "退款申请"),
    ORDER_STATUS_REFUNDING(-2, "退货中"),
    ORDER_STATUS_REFUNDFINISHED(-3, "已退货"),
    ORDER_STATUS_CANCELTRANSACTION(-4, "取消交易"),
    ORDER_STATUS_CANCELAPPLY(-5, "撤销申请"),
    ORDER_STATUS_FINISHED(4, "已完成"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private OrderStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderStatusEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (OrderStatusEnums temp : OrderStatusEnums.values()) {
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
