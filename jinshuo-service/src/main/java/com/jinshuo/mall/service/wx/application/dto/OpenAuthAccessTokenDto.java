package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 获取/刷新接口调用令牌返回数据
 * @author dongyh
 * @Title: OpenPlatformAuthInfo
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 15:59
 */
@Data
public class OpenAuthAccessTokenDto {
    /**
     * 授权方令牌
     */
    private String authorizer_access_token;

    /**
     * 有效期，单位：秒
     */
    private Long expires_in	;

    /**
     * 刷新令牌
     */
    private String authorizer_refresh_token;

}
