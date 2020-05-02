package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色对应的菜单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuTreeDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String menu;
    private String name;
    private boolean check;

    private List<RoleMenuTreeDto> subMenu;
    private List<ButtonDto> btn;
}
