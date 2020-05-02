package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/9/24.
 */
@Data
public class SpuShopCmd {

    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id不能为空!")
    private Long spuId;

    @ApiModelProperty(value = "店铺id")
    @NotNull(message = "店铺id不能为空!")
    private Long shopId;
}
