package com.jinshuo.mall.service.wx.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author dongyh
 * @Title: WxMiniCmd
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 10:25
 */
@Data
@Builder
@Accessors(chain = true)
public class WxMiniCmd {

    @ApiModelProperty(value = "appid")
    @NotNull(message = "小程序appid不能为空")
    private String appid;

    @ApiModelProperty(value = "SECRET")
    @NotNull(message = "小程序secret不能为空")
    private String secret;

    @ApiModelProperty(value = "code")
    @NotNull(message = "CODE不能为空")
    private String code;

    @ApiModelProperty(value = "包括敏感数据在内的完整用户信息的加密数据")
    private String encryptedData;

    @ApiModelProperty(value = "加密算法的初始向量")
    private String iv;

}