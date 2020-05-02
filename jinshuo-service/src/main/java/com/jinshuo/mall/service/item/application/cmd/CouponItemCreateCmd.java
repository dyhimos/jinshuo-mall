package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/7/30.
 */
@Data
public class CouponItemCreateCmd {

    @NotNull(message = "目标对象不能为空！")
    @ApiModelProperty(value = "目标对象")
    private Long targetId;//目标对象

    @NotNull(message = "可用产品类型不能为空！")
    @ApiModelProperty(value = "可用产品类型")
    private Integer type;//类型

    @NotNull(message = "是否适用类型不能为空！")
    @ApiModelProperty(value = "是否适用类型 0可用 1不可用")
    private Integer isApply;//是否适用类型
}
