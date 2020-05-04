package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 代小程序实现业务设置业务域名
 *
 * @Description:
 * @Author: WxMiniWebViewDomianCmd
 * @CreateDate: 2019/9/18 17:04
 * @UpdateUser: mgh
 * @UpdateDate: 2019/9/18 17:04
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Builder
public class WxMiniWebViewDomianCmd {

    /**
     * 接口调用凭证(必填)
     */
    @NotNull(message = "接口调用凭证不能为空")
    private String access_token;


    /**
     * 如果没有指定 action，则默认将第三方平台登记的小程序业务域名全部添加到该小程序
     */
    @NotNull(message = "action不能为空")
    private String action;


    /**
     * 小程序业务域名，当 action 参数是 get 时不需要此字段
     */
    private String[] webviewdomain;
}
