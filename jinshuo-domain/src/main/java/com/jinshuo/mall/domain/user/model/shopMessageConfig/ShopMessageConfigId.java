package com.jinshuo.mall.domain.user.model.shopMessageConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 短信配置信息id
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ShopMessageConfigId {
    private Long id;
}
