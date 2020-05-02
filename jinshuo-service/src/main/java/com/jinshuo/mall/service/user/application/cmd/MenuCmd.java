package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 菜单cmd
 * Created by 19458 on 2019/8/16.
 */
@Data
@Builder
public class MenuCmd {
    @ApiModelProperty(value = "菜单id ")
    private Long id;

    @ApiModelProperty(value = "菜单类型 1:一级菜单 2:二级菜单 3:三级菜单 ")
    private Integer type;

    @ApiModelProperty(value = "菜单类型 1:系统 2:菜单 3:按钮 ")
    private Integer menuType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父节点id")
    private Long pid;

    @ApiModelProperty(value = "节点编码")
    private String code;

    @ApiModelProperty(value = "描述")
    private String desc;
}
