package com.jinshuo.mall.service.user.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongyh
 * @Title: WxUserInfoDto
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@NoArgsConstructor
public class UserAccountPlatformDto {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "1:wechat")
    private String type;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "微信unionid")
    private String unionid;

    @ApiModelProperty(value = "小程序头像")
    private String avatar;

    @ApiModelProperty(value = "公众号头像")
    private String headimgurl;

    @ApiModelProperty(value = "性别")
    private Integer sex;
}
