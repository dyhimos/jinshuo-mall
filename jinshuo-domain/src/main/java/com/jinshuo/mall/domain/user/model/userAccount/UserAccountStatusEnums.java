package com.jinshuo.mall.domain.user.model.userAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @author dongyh
 * @Title: AccountUserEnums
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 11:17
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserAccountStatusEnums implements BaseEnum<UserAccountStatusEnums,Integer> {
    ACCOUNT_ENABLE(0,"正常"),
    ACOOUNT_DISABLE(1,"停用"),
    ;
    private final Integer code;
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    UserAccountStatusEnums(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static UserAccountStatusEnums getEnumByCode(Integer code){
        if(null == code){
            return null;
        }
        for(UserAccountStatusEnums temp: UserAccountStatusEnums.values()){
            if(temp.getCode().equals(code)){
                return temp;
            }
        }
        return null;
    }


    public static String getDescByCode(Integer code) {
        if (code == null) {
            return "";
        }
        /*for(GoodsOrderEnums temp: GoodsOrderEnums.values()){
            if(temp.getCode().equals(code)){
                return temp.getDesc();
            }
        }*/
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
