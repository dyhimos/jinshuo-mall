package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 后端用户新增cmd
 */
@Data
public class UserManagerAccountCmd {

    @ApiModelProperty(value = "id ")
    private Long id;

    @ApiModelProperty(value = "登录名 ")
    private String username;

    @ApiModelProperty(value = "手机号码 ")
    private String phone;

    @ApiModelProperty(value = "用户名称 ")
    private String nickname;

    @ApiModelProperty(value = "角色列表 ")
    private List<Long> roleIds;

    @ApiModelProperty(value = "权限列表")
    private List<Long> menuIds;
}
