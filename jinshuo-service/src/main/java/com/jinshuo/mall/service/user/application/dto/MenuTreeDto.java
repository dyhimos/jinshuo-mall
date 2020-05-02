package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by 19458 on 2019/10/15.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuTreeDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String code;
    private String name;
    private Integer type;
    private Integer sort;
    private List<MenuTreeDto> childs;
}
