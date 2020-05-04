package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 通过网页授权获取access_token
 * @author dongyh
 * @Title: ComponentAccessTokenDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class AccessTokenDto {
    /**
     * 接口调用凭证
     */
    private String access_token;

    /**
     * 接口调用凭证超时时间，单位（秒）
     */
    private Long expires_in;

    /**
     * 用户刷新 access_token
     */
    private String refresh_token;

    /**
     * 授权用户唯一标识
     */
    private String openid;

    /**
     * 用户如果在第三方平台中存在
     */
    private String unionid;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
}
