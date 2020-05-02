package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 店铺id
 */
@Data
@AllArgsConstructor
public class ShopIdCmd {
    @ApiModelProperty(value = "id")
    @NotNull(message = "店铺id")
    private Long shopId;
}
