package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname SpuQry
 * @Description TODO
 * @Date 2019/6/27 15:43
 * @Created by dongyh
 */
@Data
public class GoodsOrderDetailQry {
    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不能为空")
    private Long id;
}
