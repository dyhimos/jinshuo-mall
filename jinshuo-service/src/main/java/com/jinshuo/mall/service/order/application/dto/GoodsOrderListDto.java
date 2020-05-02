package com.jinshuo.mall.service.order.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.core.model.enums.Status;
import com.jinshuo.mall.domain.order.product.order.PayChannelEnums;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpress;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @Classname GoodsOrderDto
 * @Description 返回的订单列表
 * @Date 2019/6/28 9:29
 * @Created by dongyh
 * @author  dongyh
 */
@Data
public class GoodsOrderListDto {

    @ApiModelProperty(value = "订单id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "订单流水号")
    private String orderNo;

    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    @ApiModelProperty(value = "客户编号")
    private BigInteger memberId;

    @ApiModelProperty(value = "会员名称")
    private String memberName;

    @ApiModelProperty(value = "订单状态")
    private GoodsOrderStatusDto orderStatus;

    @ApiModelProperty(value = "用户售后状")
    private Integer afterStatus;

    @ApiModelProperty(value = "商品数")
    private Integer goodsCount;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal goodsAmountTotal;

    @ApiModelProperty(value = "运费金额")
    private BigDecimal logisticsFee;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "实际付款金额")
    private BigDecimal orderAmountTotal;

    @ApiModelProperty(value = "收货地址编号")
    private BigInteger addressId;

    @ApiModelProperty(value = "支付渠道")
    private PayChannelEnums payChannel;

    @ApiModelProperty(value = "订单支付单号")
    private String outTradeNo;

    @ApiModelProperty(value = "第三方支付流水号")
    private String escrowTradeNo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "付款时间")
    private Date payTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "订单结算状态")
    private Integer orderSettlementStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "订单结算时间")
    private Date orderSettlementTime;

    @ApiModelProperty(value = "是否是积分产品")
    private Integer isIntegral;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "订单备注")
    private String remarks;

    @ApiModelProperty(value = "系统备注")
    private String systemRemarks;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "完成时间")
    private Date finshDate;

    private Integer orderClass;

    private Status status;

    @ApiModelProperty(value = "订单详情信息")
    public List<GoodsOrderDetailDto> goodsOrderDetailList;

    public GoodsOrderAddressDto goodsOrderAddress;

    public GoodsOrderExpress goodsOrderExpress;

    @ApiModelProperty(value = "成本价")
    private BigDecimal totalCostPrice;

}
