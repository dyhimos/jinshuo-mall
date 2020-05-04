package com.jinshuo.mall.service.wx.utils;

import com.jinshuo.core.utils.SpringUtil;
import com.jinshuo.mall.domain.user.model.wx.WxConfig;
import com.jinshuo.mall.domain.user.model.wxOpenAuth.WxOpenAuth;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.wx.application.cmd.WxConfigCmd;
import com.jinshuo.mall.service.wx.application.service.WxConfigCmdService;
import com.jinshuo.mall.service.wx.application.service.WxMp;
import com.jinshuo.mall.service.wx.webservice.WxOpenAgentService;

/**
 * 获取微信token
 * @author minggh
 * @Title: getTokenUtils
 * @ProjectName ym-center
 * @Description: TODO
 * @dae 2019/10/17 19:23
 */
public class AccessTokenUtils {
    private static WxConfigCmdService wxConfigCmdService = SpringUtil.getBean(WxConfigCmdService.class);

    /**
     * 第三方平台授权toekn
     * @param shopId
     * @return
     * @throws Exception
     */
    public static String getOpenToken(Long shopId) throws Exception {
        WxOpenAuth wxOpenAuth = WxOpenAuth.getWxAuth(shopId);
        return WxOpenAgentService.getApiAuthorizerToken(wxOpenAuth.getAppid(),wxOpenAuth.getAuthorizerRefreshToken());
    }

    /**
     * 公众平台token
     * @param shopId
     * @return
     * @throws Exception
     */
    public static String getWxMpToken(Long shopId) throws Exception {
        ShopIdCmd shopIdCmd =new ShopIdCmd();
        shopIdCmd.setShopId(shopId);
        WxConfig wxConfig = wxConfigCmdService.getWxConfig(shopIdCmd);
        WxConfigCmd wxConfigCmd = WxConfigCmd.builder()
                .appid(wxConfig.getAppId())
                .secret(wxConfig.getAppSecret())
                .build();
        String token = WxMp.getToken(wxConfigCmd);
        return token;
    }
}
