package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 代小程序实现业务设置服务器域名
 *
 * @Description:
 * @Author: WxMiniSetServicesDomianCmd
 * @CreateDate: 2019/9/18 17:04
 * @UpdateUser: mgh
 * @UpdateDate: 2019/9/18 17:04
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Builder
public class WxMiniSetServicesDomianCmd {

    /**
     * 接口调用凭证(必填)
     */
    @NotNull(message = "接口调用凭证不能为空")
    private String access_token;


    /**
     * 操作类型（add delete set get）
     */
    @NotNull(message = "action不能为空")
    private String action;


    /**
     * request 合法域名；当 action 是 get 时不需要此字段
     */
    private String[] requestdomain;

    /**
     * socket 合法域名；当 action 是 get 时不需要此字段
     */
    private String[] wsrequestdomain;


    /**
     * uploadFile 合法域名；当 action 是 get 时不需要此字段
     */
    private String[] uploaddomain;


    /**
     * downloadFile 合法域名；当 action 是 get 时不需要此字段
     */
    private String[] downloaddomain;
}
