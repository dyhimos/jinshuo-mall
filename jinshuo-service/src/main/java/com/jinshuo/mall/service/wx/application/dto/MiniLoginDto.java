package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 小程序登录返回json
 * @author dongyh
 * @Title: ComponentAccessTokenDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class MiniLoginDto {
    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;


    /**
     * 用户如果在第三方平台中存在
     */
    private String unionid;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;
}
