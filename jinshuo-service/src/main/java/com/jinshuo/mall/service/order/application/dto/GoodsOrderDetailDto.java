package com.jinshuo.mall.service.order.application.dto;

import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetailId;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname GoodsOrderDto
 * @Description 订单列表
 * @Date 2019/6/28 9:29
 * @Created by dongyh
 * @author  dongyh
 */
@Data
public class GoodsOrderDetailDto {

    @ApiModelProperty(value = "订单详情id")
    private GoodsOrderDetailId goodsOrderDetailId;

    @ApiModelProperty(value = "运费")
    private BigDecimal logisticsFee;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "商品图片")
    private String goodsImage;

    @ApiModelProperty(value = "商品编码")
    private String goodsSpuId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品SKU")
    private Long goodsSkuId;

    @ApiModelProperty(value = "折扣比例")
    private Integer discountRate;

    @ApiModelProperty(value = "折扣比例金额")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "购买数量")
    private Integer number;

    @ApiModelProperty(value = "小计金额")
    private BigDecimal subtotal;

    @ApiModelProperty(value = "是否发码产品 0 是 1：不是")
    private Integer isSendcode;

    @ApiModelProperty(value = "预约地址")
    private String reserveAddress;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 订单核销码信息
     */
    private List<OrderVerificationCode> orderVerificationCodeList;
}
