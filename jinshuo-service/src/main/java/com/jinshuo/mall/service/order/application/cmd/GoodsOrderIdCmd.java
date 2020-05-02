package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/8/14.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrderIdCmd {
    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不能为空")
    private Long id;

}
