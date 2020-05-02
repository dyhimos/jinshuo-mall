package com.jinshuo.mall.service.order.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname GoodsOrderIdDto
 * @Description 地址信息dto
 * @Date 2019/6/28 9:29
 * @Created by dongyh
 * @author  dongyh
 */
@Data
public class GoodsOrderAddressDto {


    @ApiModelProperty(value = "收货人名称")
    private String userName;


    @ApiModelProperty(value = "收件人地址")
    private String userAddress;

    @ApiModelProperty(value = "收件人手机号码")
    private String userPhone;

}
