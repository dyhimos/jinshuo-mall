package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统计订单金额
 */
@Data
public class ManagerOrderCountAmountQry {

    @ApiModelProperty(value = " 1:按月 2:按日")
    private Integer qryType;

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;
}
