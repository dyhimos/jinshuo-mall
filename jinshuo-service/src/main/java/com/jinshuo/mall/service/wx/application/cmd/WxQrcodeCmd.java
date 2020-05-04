package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

/**
 * 微信生成二维码参数
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
public class WxQrcodeCmd {

    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    private Long expire_seconds;


    /**
     * 二维码类型(以下四选1)
     * QR_SCENE为临时的整型参数值，
     * QR_STR_SCENE为临时的字符串参数值，
     * QR_LIMIT_SCENE为永久的整型参数值，
     * QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    private String action_name;

    /**
     * 二维码详细信息
     */
    private WxActionInfoCmd action_info;
}
