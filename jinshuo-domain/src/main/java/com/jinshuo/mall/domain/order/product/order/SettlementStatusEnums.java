package com.jinshuo.mall.domain.order.product.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @Description: 结算状态
 * @Author: dongyh
 * @CreateDate: 2019/7/23 16:43
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/7/23 16:43
 * @UpdateRemark:
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SettlementStatusEnums implements BaseEnum<SettlementStatusEnums, Integer> {
    /*结算状态*/
    ORDER_SETTLEMENT_STATUS_NO(0, "未结算"),
    ORDER_SETTLEMENT_STATUS_YES(1, "已结算"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private SettlementStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SettlementStatusEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (SettlementStatusEnums temp : SettlementStatusEnums.values()) {
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
