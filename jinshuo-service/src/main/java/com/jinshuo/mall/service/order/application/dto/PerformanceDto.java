package com.jinshuo.mall.service.order.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/11/19.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDto {

    @ApiModelProperty(value = "订单数")
    private Integer orderCount;

    @ApiModelProperty(value = "商品数")
    private Integer spuCount;

    @ApiModelProperty(value = "会员数")
    private Integer memberCount;

    @ApiModelProperty(value = "成交额")
    private BigDecimal costPrice;

}
