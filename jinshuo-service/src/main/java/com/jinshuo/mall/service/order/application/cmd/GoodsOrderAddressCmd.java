package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname GoodsOrderAddress
 * @Description 订单配送地址信息
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class GoodsOrderAddressCmd {

    @ApiModelProperty(value = "收货人姓名")
    //@NotNull(message = "收货人姓名")
    private String userName;


    @ApiModelProperty(value = "收件人地址")
    //@NotNull(message = "收件人地址")
    private String userAddress;

    @ApiModelProperty(value = "收件人手机号码")
    //@NotNull(message = "收件人手机号码")
    private String userPhone;

}
