package com.jinshuo.mall.domain.user.model.shopMessageConfig;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信配置信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopMessageConfig extends IdentifiedEntity {
    /**
     * id
     */
    private ShopMessageConfigId shopMessageConfigId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 签名
     */
    private String signName;

    /**
     * 短信总条数
     */
    private Integer total;

    /**
     * 已使用条数
     */
    private Integer used;

    /**
     * 剩余条数
     */
    private Integer remaining;

}
