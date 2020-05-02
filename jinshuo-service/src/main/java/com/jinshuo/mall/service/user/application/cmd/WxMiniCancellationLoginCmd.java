package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname WxMiniLoginCmd
 * @Description 微信核销小程序登录
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class WxMiniCancellationLoginCmd {

    @ApiModelProperty(value = "username")
    @NotNull(message = "手机号不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

}
