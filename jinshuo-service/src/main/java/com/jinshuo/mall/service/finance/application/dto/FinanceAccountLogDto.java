package com.jinshuo.mall.service.finance.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金账户列表Dto
 */
@Data
public class FinanceAccountLogDto {


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "memId")
    private Long memId;


    @ApiModelProperty(value = "类型 0:收入,1:支出")
    private Integer type;

    @ApiModelProperty(value = "0:后台充值 1余额支出 2：支付宝支付 3：微信支付")
    private Integer source;

    @ApiModelProperty(value = "相关单据流水号")
    private String sourceSn;

    @ApiModelProperty(value = "总账户变动之前余额")
    private BigDecimal beforeChangeAmount;

    @ApiModelProperty(value = "总账户变动之后余额")
    private BigDecimal afterChangeAmount;

    @ApiModelProperty(value = "变动金额")
    private BigDecimal changeAmount;

    @ApiModelProperty(value = "具体变动描述")
    private String desc;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
}
