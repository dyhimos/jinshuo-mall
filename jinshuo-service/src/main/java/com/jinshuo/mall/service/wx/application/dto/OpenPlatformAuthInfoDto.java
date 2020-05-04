package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 第三方平台授权信息
 * @author dongyh
 * @Title: OpenPlatformAuthInfo
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 15:59
 */
@Data
public class OpenPlatformAuthInfoDto {

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
     * (在授权的公众号/小程序具备API权限时，才有此返回值），单位：秒
     */
    private Long expires_in;

    /**
     * 刷新令牌（在授权的公众号具备API权限时，才有此返回值），
     * 刷新令牌主要用于第三方平台获取和刷新已授权用户的 authorizer_access_token。
     * 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌。
     * 用户重新授权后，之前的刷新令牌会失效
     */
    private String authorizer_refresh_token;

    /**
     * 授权给开发者的权限集列表
     */
    //private List<OpenFunInfoDto> func_info;

}
