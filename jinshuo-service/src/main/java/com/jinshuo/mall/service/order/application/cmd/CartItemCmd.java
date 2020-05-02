package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/9/18.
 */
@Data
public class CartItemCmd {
    @ApiModelProperty(value = "skuId")
    @NotNull(message = "商品编码不能为空！")
    private Long skuId;

    @ApiModelProperty(value = "商品数量")
    @NotNull(message = "商品数量不能为空")
    private Integer quantity;


    @ApiModelProperty(value = "添加时价格")
    @NotNull(message = "添加时价格不能为空")
    private BigDecimal addPrice;

    private BigDecimal goodsPrice;
}
