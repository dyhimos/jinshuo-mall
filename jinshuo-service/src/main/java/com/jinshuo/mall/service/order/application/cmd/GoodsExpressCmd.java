package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsExpressCmd {


    @ApiModelProperty(value = "订单编号")
    private Long orderId;


    @ApiModelProperty(value = "快递名称")
    private String expressCompanyName;


    @ApiModelProperty(value = "快递单号")
    private String expressNo;
}
