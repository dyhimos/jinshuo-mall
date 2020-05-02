package com.jinshuo.mall.service.finance.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 资金账户列表Dto
 */
@Data
public class FinanceCashPageDto {


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "memId")
    private Long memId;

    @ApiModelProperty(value = "会员编号")
    private String memNo;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

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
