package com.jinshuo.mall.domain.user.model.wx;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongyh
 * @Classname WxConfig
 * @Description 微信配置信息表
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxConfig extends IdentifiedEntity {

    /**
     * id
     */
    private WxConfigId wxConfigId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 0:公众平台 1：小程序
     */
    private Integer type;

    /**
     * 平台AppID
     */
    private String appId;

    /**
     * 平台秘钥
     */
    private String appSecret;

    /**
     * 平台名称
     */
    private String appName;

    /**
     * 支付方式 0：自建 1：代收  3：授权
     */
    private String payModel;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 子商户号
     */
    private String subMchId;

    /**
     * 商户秘钥
     */
    private String apiKey;

    /**
     * 证书路径
     */
    private String certPath;

}
