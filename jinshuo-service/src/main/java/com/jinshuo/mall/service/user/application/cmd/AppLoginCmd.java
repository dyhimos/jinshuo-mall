package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname AppLoginCmd
 * @Description app登录
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class AppLoginCmd {

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
