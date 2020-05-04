package com.jinshuo.mall.service.wx.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 微信配置信息
 *
 * @author dongyh
 * @Title: WxConfigCmd
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 10:25
 */
@Data
@Builder
@Accessors(chain = true)
public class WxConfigCmd {

    @ApiModelProperty(value = "appid")
    @NotNull(message = "公众平台appid不能为空")
    private String appid;

    @ApiModelProperty(value = "SECRET")
    @NotNull(message = "公众平台secret不能为空")
    private String secret;

    @ApiModelProperty(value = "code")
    @NotNull(message = "CODE不能为空")
    private String code;

}