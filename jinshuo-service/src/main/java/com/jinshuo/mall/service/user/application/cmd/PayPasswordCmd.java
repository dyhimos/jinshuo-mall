package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 支付密码
 */
@Data
public class PayPasswordCmd {

    @NotNull(message = "旧支付密码")
    private String oldPayPassword;


    @NotNull(message = "手机号")
    private String phone;

    @NotNull(message = "验证码")
    private String code;

    @ApiModelProperty(value = "支付密码")
    @NotNull(message = "支付密码")
    private String payPassword;
}
