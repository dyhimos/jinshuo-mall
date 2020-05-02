package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 微信退款cmd
 */
@Data
public class WxRefundCmd {
    @ApiModelProperty(value = "订单号")
    @NotNull(message = "订单号！")
    private String orderNo;

    @ApiModelProperty(value = "退款金额")
    @NotNull(message = "退款金额")
    private BigDecimal refundFee;

}
