package com.jinshuo.mall.domain.user.model.userAccountPlatform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @author dongyh
 * @Title: SexEnums
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 11:17
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  SexEnums implements BaseEnum<SexEnums,Integer> {
    SEX_UNKNOW(0,"未知"),
    SEX_MAN(1,"男"),
    SEX_WOMAN(2,"女"),
    ;
    private final Integer code;
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    SexEnums(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static SexEnums getEnumByCode(Integer code){
        if(null == code){
            return null;
        }
        for(SexEnums temp: SexEnums.values()){
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
