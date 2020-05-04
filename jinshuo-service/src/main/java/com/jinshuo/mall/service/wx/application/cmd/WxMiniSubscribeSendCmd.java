package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 发送小程序订阅消息
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
public class WxMiniSubscribeSendCmd {

    /**
     * 接收者openid
     */
    @NotNull(message = "接收用户openid不能为空")
    private String touser;


    /**
     * 模板ID
     */
    @NotNull(message = "订阅的模板id不能为空")
    private String template_id;

    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,
     * （示例index?foo=bar）。该字段不填则模板无跳转。
     */
    private String page;


    /**
     * 模板内容
     * 格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     */
    @NotNull(message = "模板数据不能为空")
    private String data;
}
