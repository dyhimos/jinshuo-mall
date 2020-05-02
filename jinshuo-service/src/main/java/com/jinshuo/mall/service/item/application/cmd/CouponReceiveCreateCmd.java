package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
public class CouponReceiveCreateCmd {

    //@NotNull(message = "优惠券编号不能为空！")
    @ApiModelProperty(value = "优惠券编号")
    private Long couponId;

    @NotNull(message = "会员id不能为空！")
    @ApiModelProperty(value = "会员id")
    private Long memId;

}
