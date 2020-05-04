package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 基本信息dto
 * @author dongyh
 * @Title: ComponentAccessTokenDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class AccountBasicinfoDto {
    /**
     * 返回码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 帐号 appid
     */
    private String appid;

    /**
     * 帐号类型（1：订阅号，2：服务号，3：小程序）
     */
    private Integer account_type;

    /**
     * 主体类型 (0:个人 1：企业 2：媒体 3：政府 4：其他组织)
     */
    private Integer principal_type;

    /**
     * 主体名称
     */
    private String principal_name;

    /**
     * 实名认证状态（1:实名认证成功 2：实名认证中 3：实名认证失败）
     */
    private String realname_status;

    /**
     * 微信认证信息
     */
    private WxVerifyInfoDto wx_verify_info;

    /**
     * 功能介绍信息
     */
    private SignatureInfoDto signature_info;

    /**
     * 头像信息
     */
    private HeadImageInfoDto head_image_info;

}
