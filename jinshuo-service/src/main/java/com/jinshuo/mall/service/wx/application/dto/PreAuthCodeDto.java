package com.jinshuo.mall.service.wx.application.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 返回给页面所需的参数
 * @author dongyh
 * @Title: ComponentVerifyTicketDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
@Builder
public class PreAuthCodeDto {
    /**
     * 第三方平台 appid
     */
    private String component_appid;

    /**
     * 预授权码
     */
    private String pre_auth_code;

    /**
     * 回调 URI
     */
    private String redirect_uri;

    /**
     * （非必填)
     * 要授权的帐号类型， 1 则商户扫码后，手机端仅展示公众号、
     * 2 表示仅展示小程序，
     * 表示公众号和小程序都展示。
     * 如果为未制定，则默认小程序和公众号都展示。
     * 第三方平台开发者可以使用本字段来控制授权的帐号类型。
     */
    private String auth_type;

    /**
     * 指定授权唯一的小程序或公众号(非必填)
     */
    private String biz_appid;
}
