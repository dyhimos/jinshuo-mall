package com.jinshuo.mall.domain.user.model.role;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.user.model.shop.ShopId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/9/20.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends IdentifiedEntity {
    private RoleId roleId;

    /**
     * 店铺id
     */
    private ShopId shopId;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;
}
