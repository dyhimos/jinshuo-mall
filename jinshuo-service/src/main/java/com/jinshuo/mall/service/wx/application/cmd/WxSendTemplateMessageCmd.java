package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

/**
 * 微信发送模板消息数据
 *
 * @Description:
 * @Author: mgh
 * @CreateDate: 2019/9/18 17:04
 * @UpdateUser: mgh
 * @UpdateDate: 2019/9/18 17:04
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Builder
public class WxSendTemplateMessageCmd {

    /**
     * 接收者openid
     */
    private String touser;


    /**
     * 模板ID
     */
    private String template_id;

    /**
     * 模板跳转链接（海外帐号没有跳转能力）(非必填)
     */
    private String url;

    /**
     * 小程序参数
     */
    private WxTemplateMessageMiniCmd wxTemplateMessageMiniCmd;

    /**
     * 模板数据
     */
    private String data;
}
