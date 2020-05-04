package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 解除绑定体验者
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
public class WxMiniUnbindTesterCmd {

    /**
     * 接口调用凭证(必填)
     */
    @NotNull(message = "接口调用凭证不能为空")
    private String access_token;


    /**
     * 微信号
     */
    @NotNull(message = "scene不能为空")
    private String wechatid;


    /**
     * 人员对应的唯一字符串， 可通过获取已绑定的体验者列表获取人员对应的字符串
     */
    private String userstr;
}
