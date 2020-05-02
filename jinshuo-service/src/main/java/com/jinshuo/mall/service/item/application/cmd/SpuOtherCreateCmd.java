package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/7/22.
 */
@Data
public class SpuOtherCreateCmd implements Serializable {

    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id不能为空!")
    private Long spuId;

    @ApiModelProperty(value = "是否显示售卖数量")
    //@NotNull(message = "是否显示售卖数量不能为空!")
    private Integer isShowSell;

    @ApiModelProperty(value = "快递费")
    //@NotNull(message = "快递费不能为空!")
    private BigDecimal courierFee;

    @ApiModelProperty(value = "上架时间")
    //@NotNull(message = "上架时间不能为空!")
    private Date upTime;

    @ApiModelProperty(value = "下架时间")
    //@NotNull(message = "下架时间不能为空!")
    private Date downTime;

    @ApiModelProperty(value = "抢购开始时间")
    //@NotNull(message = "抢购开始时间不能为空!")
    private Date buyStartDate;

    @ApiModelProperty(value = "抢购结束时间")
    //@NotNull(message = "抢购结束时间不能为空!")
    private Date buyEndDate;

    @ApiModelProperty(value = "活动地址")
    //@NotNull(message = "活动地址不能为空!")
    private String activityAddress;

    @ApiModelProperty(value = "活动时间")
    //@NotNull(message = "活动时间不能为空!")
    private String activityDate;

    @ApiModelProperty(value = "是否抢购商品")
    @NotNull(message = "是否抢购商品不能为空!")
    private Integer isScareBuy;

    @ApiModelProperty(value = "卷使用开始时间")
    private Date activityStartDate;

    @ApiModelProperty(value = "卷使用结束时间")
    private Date activityEndDate;
}
