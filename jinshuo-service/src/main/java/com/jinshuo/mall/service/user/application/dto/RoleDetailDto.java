package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色详情
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDetailDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String code;
    private String name;
    private List<RoleMenuTreeDto> menus;
}
