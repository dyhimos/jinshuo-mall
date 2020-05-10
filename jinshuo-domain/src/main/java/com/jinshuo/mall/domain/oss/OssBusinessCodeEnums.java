package com.jinshuo.mall.domain.oss;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
 * @Description: 退单状态枚举
 * @Author: mgh
 * @CreateDate: 2019/12/23 16:43
 * @Version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OssBusinessCodeEnums implements BaseEnum<OssBusinessCodeEnums, Integer> {
    ITEMPICTURE(10001, "产品图片"),
    TOPICPICTURE(10002, "论坛图片"),
    AVATAR(10003, "头像"),;
    public Integer code;
    public String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private OssBusinessCodeEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OssBusinessCodeEnums getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (OssBusinessCodeEnums temp : OssBusinessCodeEnums.values()) {
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
        for (OssBusinessCodeEnums temp : OssBusinessCodeEnums.values()) {
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
