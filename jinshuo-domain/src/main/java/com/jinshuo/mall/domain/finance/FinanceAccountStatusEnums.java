package com.jinshuo.mall.domain.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @Description: 退单状态枚举
 * @Author: dongyh
 * @CreateDate: 2019/12/23 16:43
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FinanceAccountStatusEnums implements BaseEnum<FinanceAccountStatusEnums, Integer> {
    FINANCE_ACCOUNT_NORMAL(0, "正常"),
    FINANCE_ACCOUNT_DISABLE(1, "停用"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private FinanceAccountStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FinanceAccountStatusEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (FinanceAccountStatusEnums temp : FinanceAccountStatusEnums.values()) {
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
        for (FinanceAccountStatusEnums temp : FinanceAccountStatusEnums.values()) {
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
