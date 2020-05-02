package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname GoodsOrderUpdateStatusCmd
 * @Description 订单更新参数
 * @Date 2019/6/27 15:36
 * @Created by dongyh
 * @author dongyh
 */

@Data
@Accessors(chain = true)
public class GoodsOrderUpdateCmd {

    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id")
    private Long id;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单备注")
    private String systemRemarks;
}
