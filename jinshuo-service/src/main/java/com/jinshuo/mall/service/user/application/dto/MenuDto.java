package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * Created by 19458 on 2019/9/20.
 */
@Data

public class MenuDto  {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Integer type;
    private String name;
    private Long pid;
    private String code;
    private String desc;
}
