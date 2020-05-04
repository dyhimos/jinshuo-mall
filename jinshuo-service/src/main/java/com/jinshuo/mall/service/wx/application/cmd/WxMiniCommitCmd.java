package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

/**
 * 上传小程序代码参数
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
public class WxMiniCommitCmd {

    /**
     * 接口调用凭证(必填)
     */
    private String access_token;


    /**
     * 代码库中的代码模版 ID
     */
    private String template_id;


    /**
     * 第三方自定义的配置
     */
    private String ext_json;

    /**
     * 代码版本号，开发者可自定义（长度不要超过 64 个字符）
     */
    private String user_version;

    /**
     * 代码描述，开发者可自定义
     */
    private String user_desc;
}
