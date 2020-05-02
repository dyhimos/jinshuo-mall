package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.mall.domain.user.model.merchant.MerchantMenuId;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/9/20.
 */
@Data
@NoArgsConstructor
public class MerchantMenuDto {

    private MerchantMenuId merchantMenuId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long merchantId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
}
