package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname GoodsOrderSimpleCmd
 * @Description 寄样信息
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class GoodsOrderSimpleCmd {
    @ApiModelProperty(value = "店铺ID")
    @NotNull(message = "店铺不能为空")
    private Long shopId;

    @ApiModelProperty(value = "寄样信息")
    @NotNull(message = "寄样信息不能为空")
    private String simpleInfo;

    @ApiModelProperty(value = "收货人姓名")
    @NotNull(message = "收货人姓名不能为空")
    private String userName;


    @ApiModelProperty(value = "收件人地址")
    @NotNull(message = "收件人地址不能为空")
    private String userAddress;

    @ApiModelProperty(value = "收件人手机号码")
    @NotNull(message = "收件人手机号码不能为空")
    private String userPhone;

}
