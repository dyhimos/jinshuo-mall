package com.jinshuo.mall.service.order.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 管理后台对账单dto
 */
@Data

public class ManagerOrderCountAmountDto {
    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "收入")
    private BigDecimal income;

    @ApiModelProperty(value = "支出")
    private BigDecimal pay;

    @ApiModelProperty(value = "营收")
    private BigDecimal realIncome;
}
