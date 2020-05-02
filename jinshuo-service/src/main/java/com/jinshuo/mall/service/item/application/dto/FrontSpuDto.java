package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 19458 on 2019/10/8.
 */
@Data
public class FrontSpuDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "简介")
    private String  sketch;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;

    @ApiModelProperty(value = "标签集")
    private String tag;

    @ApiModelProperty(value = "产品主图")
    private String pictureUrl;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否热门")
    private Integer isHot;

    @ApiModelProperty(value = "是否分销产品")
    private Integer isDis;

    @ApiModelProperty(value = "海报")
    private String poster;

    @ApiModelProperty(value = "属性id")
    private String featureId;

    @ApiModelProperty(value = "属性名称")
    private String featureName;

    @ApiModelProperty(value = "结束时间")
    private String buyEndDate;

    @ApiModelProperty(value = "活动地址")
    private String activityAddress;

    @ApiModelProperty(value = "标签集")
    private List<String> tags;

    @ApiModelProperty(value = "佣金")
    private BigDecimal disMoney;

    @ApiModelProperty(value = "地区")
    private String areaNames;
}
