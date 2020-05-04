package com.jinshuo.mall.service.wx.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 微信获取令牌参数
 *
 * @author dongyh
 * @Title: WxApiComponentTokenCmd
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 10:25
 */
@Data
@Builder
@Accessors(chain = true)
public class WxApiComponentTokenCmd {

    @ApiModelProperty(value = "第三方平台 appid")
    @NotNull(message = "第三方平台 appid 不能为空")
    private String component_appid;

    @ApiModelProperty(value = "第三方平台 appsecret")
    @NotNull(message = "第三方平台不能为空")
    private String component_appsecret;

    @ApiModelProperty(value = "微信后台推送的 ticket")
    @NotNull(message = "微信后台推送的 ticket不能为空")
    private String component_verify_ticket;
}