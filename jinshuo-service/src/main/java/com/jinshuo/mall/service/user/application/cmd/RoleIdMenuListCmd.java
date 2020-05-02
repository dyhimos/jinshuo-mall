package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色对应菜单
 */
@Data
public class RoleIdMenuListCmd {
    @ApiModelProperty(value = "角色id ")
    private List<Long> ids;
}
