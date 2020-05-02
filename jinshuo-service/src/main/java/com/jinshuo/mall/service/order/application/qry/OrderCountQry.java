package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/11/21.
 */
@Data
public class OrderCountQry {
    @ApiModelProperty(value = " 供应商id")
    private Long supplierId;

    @ApiModelProperty(value = " 1:今天 2:昨天 3：七日")
    private Integer qryType;
}
