package com.jinshuo.mall.service.user.application.dto;

import lombok.Data;

/**
 * @author dongyh
 * @Title: WxMpUserQueryParam
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/9/18 18:02
 */
@Data
public class WxMpUserQueryParam {
    private static final long serialVersionUID = -6863571795702385319L;
    private String openid;
    private String lang;

    public WxMpUserQueryParam(String openid, String lang) {
        this.openid = openid;
        this.lang = lang;
    }

    public WxMpUserQueryParam(String openid) {
        this.openid = openid;
        this.lang = "zh_CN";
    }
}
