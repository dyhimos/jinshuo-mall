package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/7/22.
 */
@Data
public class SpuOtherDto {

    @ApiModelProperty(value = "商品其他设置id")
    private String id;

    @ApiModelProperty(value = "是否展示销量")
    private Integer isShowSell;

    @ApiModelProperty(value = "商品代码")
    private String spuId;

    @ApiModelProperty(value = "快递费")
    private BigDecimal courierFee;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "上架时间")
    private Date upTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "下架时间")
    private Date downTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "开始抢购时间")
    private Date buyStartDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "抢购结束时间")
    private Date buyEndDate;

    @ApiModelProperty(value = "活动地址")
    private String activityAddress;

    @ApiModelProperty(value = "活动时间")
    private String activityDate;

    @ApiModelProperty(value = "是否抢购商品")
    private Integer isScareBuy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "卷使用开始时间")
    private Date activityStartDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "卷使用结束时间")
    private Date activityEndDate;
}
