package com.jinshuo.core.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Classname Status
 * @Description TODO
 * @Date 2019/6/28 16:21
 * @Created by dyh
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status implements BaseEnum<Status,Integer>{
    NORMAL(1, "正常"),
    AUDIT(2, "审核"),
    LOCKED(3, "锁定"),
    DELETE(4, "删除"),
    ENABLE(5, "激活"),
    UNENABLE(6, "未激活");

    public int code;
    public String name;
    Status(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

}
