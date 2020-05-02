package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname GoodsOrderPayCmd
 * @Description 订单支付参数
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class GoodsOrderPayCmd {

    @ApiModelProperty(value = "订单ID")
    @NotNull(message = "参数不能为空")
    private String id;

    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "支付方式")
    private String tradeType;


    @ApiModelProperty(value = "自定义参数")
    private String attach;
}
