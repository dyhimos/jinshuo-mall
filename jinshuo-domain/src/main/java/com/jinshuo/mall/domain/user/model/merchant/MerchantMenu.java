package com.jinshuo.mall.domain.user.model.merchant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.core.model.IdentifiedEntity;
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
public class MerchantMenu extends IdentifiedEntity {
    private MerchantMenuId merchantMenuId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long merchantId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
}
