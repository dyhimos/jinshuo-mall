package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Classname GoodsOrderAddress
 * @Description 订单产品详细信息
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class GoodsOrderDetailCmd {


    @ApiModelProperty(value = "商品编码")
    @NotNull(message = "商品编码")
    private Long goodsSpuId;

    @ApiModelProperty(value = "商品SKU")
    private Long goodsSkuId;

    @ApiModelProperty(value = "折扣比例")
    private Integer discountRate;

    @ApiModelProperty(value = "折扣比例金额")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "购买数量")
    @NotNull(message = "购买数量")
    private Integer number;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否购物车商品 0是 1不是")
    private Integer isCart;

}
