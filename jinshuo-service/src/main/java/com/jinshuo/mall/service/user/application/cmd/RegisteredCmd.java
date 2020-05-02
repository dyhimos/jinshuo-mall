package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author dyh
 * @Classname RegisteredCmd
 * @Description app注册
 * @Date 2019/12/20 :01
 * @Created by dyh
 */
@Data
public class RegisteredCmd {

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不能为空")
    private String verificationCode;

    @ApiModelProperty(value = "shopId")
    //@NotNull(message = "shopId不能为空")
    private Long shopId;
}
