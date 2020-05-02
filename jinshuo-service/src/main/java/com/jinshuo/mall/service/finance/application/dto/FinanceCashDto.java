package com.jinshuo.mall.service.finance.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/9/10.
 */
@Data
public class FinanceCashDto {

    @ApiModelProperty(value = "可用余额")
    private BigDecimal avaibleAmount;


    @ApiModelProperty(value = "累计余额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "冻结余额")
    private BigDecimal frozenAmount;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty(value = "充值次数")
    private Integer rechargeTime;
}
