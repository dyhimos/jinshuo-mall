package com.jinshuo.mall.domain.order.product.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @Description: 兑换状态枚举
 * @Author: dongyh
 * @CreateDate: 2019/7/23 16:43
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/7/23 16:43
 * @UpdateRemark:
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExchangeStatusEnums implements BaseEnum<ExchangeStatusEnums, Integer> {
    EXCHANGE_STATUS_PAY(1, "待发货"),
    EXCHANGE_STATUS_SHIPPED(2, "待收货"),
    EXCHANGE_STATUS_FINISHED(4, "已完成"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private ExchangeStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ExchangeStatusEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (ExchangeStatusEnums temp : ExchangeStatusEnums.values()) {
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
