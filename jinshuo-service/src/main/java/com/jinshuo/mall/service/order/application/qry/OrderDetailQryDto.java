package com.jinshuo.mall.service.order.application.qry;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/11/21.
 */
@Data
public class OrderDetailQryDto {
    private Long id;
    private String orderNo;
    private Integer orderType;
    private Long memberId;
    private String memberName;
    private Integer orderStatus;
    private Integer afterStatus;
    private Integer payChannel;
    private Date orderSettlementTime;
    private Long goodsOrderDetailId;
    private Long supplierId;
    private String supplierName;
    private BigDecimal logisticsFee;
    private Long goodsSpuId;
    private String goodsName;
    private BigDecimal goodsPrice;
    private BigDecimal costPrice;
    private Long goodsSkuId;
    private Integer number;
    private BigDecimal subtotal;
    private Integer isSendcode;
    private Integer autoSendCode;
}
