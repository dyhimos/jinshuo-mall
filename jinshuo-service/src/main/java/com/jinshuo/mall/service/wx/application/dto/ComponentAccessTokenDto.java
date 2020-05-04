package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 使用授权码获取授权信息返回dto
 * @author dongyh
 * @Title: ComponentAccessTokenDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class ComponentAccessTokenDto {
    /**
     * 授权方 appid
     */
    private String authorizer_appid;

    /**
     * 接口调用令牌（在授权的公众号/小程序具备 API 权限时，才有此返回值）
     */
    private String authorizer_access_token;

    /**
     * authorizer_access_token 的有效期
     * 在授权的公众号/小程序具备API权限时，才有此返回值），单位：秒
     */
    private Long expires_in;

    /**
     * Ticket 内容
     */
    private String authorizer_refresh_token;
}
