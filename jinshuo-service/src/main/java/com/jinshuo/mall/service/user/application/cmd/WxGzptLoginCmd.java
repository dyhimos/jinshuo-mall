package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname WxGzptLoginCmd
 * @Description 微信公众平台登录
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class WxGzptLoginCmd {

    @ApiModelProperty(value = "code")
    @NotNull(message = "CODE不能为空")
    private String code;

    @ApiModelProperty(value = "邀请码")
    private String inviteCode;

    @ApiModelProperty(value = "店铺Id")
    private Long shopId;
}
