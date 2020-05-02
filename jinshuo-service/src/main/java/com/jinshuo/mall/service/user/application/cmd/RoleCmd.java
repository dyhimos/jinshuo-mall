package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/8/16.
 */
@Data
public class RoleCmd {
    @ApiModelProperty(value = "角色id ")
    private Long id;

    @ApiModelProperty(value = "店铺编码 ")
    private Long shopId;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "菜单list")
    private List<Long> menuIdList;
}
