package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
public class CouponReceiveUpdateCmd {
    @NotNull(message = "ID不能为空！")
    @ApiModelProperty(value = "ID")
    private Long id;

    @NotNull(message = "优惠券编号不能为空！")
    @ApiModelProperty(value = "优惠券编号")
    private Long couponId;

    @NotNull(message = "会员id不能为空！")
    @ApiModelProperty(value = "会员id")
    private Long memId;

    @NotNull(message = "优惠券码不能为空！")
    @ApiModelProperty(value = "优惠券码")
    private Long couponCode;

    @ApiModelProperty(value = "优惠券码")
    private Date receiveTime;

    @ApiModelProperty(value = "使用时间")
    private Date useTime;

    @ApiModelProperty(value = "使用状态")
    private Integer useStatus;
}
