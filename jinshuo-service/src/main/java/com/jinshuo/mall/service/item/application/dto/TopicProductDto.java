package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/9/30.
 */
@Data
public class TopicProductDto {

    @JsonSerialize(using = ToStringSerializer.class)
    public String id;

    @JsonSerialize(using = ToStringSerializer.class)
    public String spuId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品简述")
    private String sketch;

    @ApiModelProperty(value = "商品分类编号")
    private String categoryId;

    @ApiModelProperty(value = "商品分类名称")
    private String categoryName;

    @ApiModelProperty(value = "店铺编号")
    private String shopId;

    @ApiModelProperty(value = "封面图")
    private String tag;

    @ApiModelProperty(value = "封面图")
    private String pictureUrl;

    @ApiModelProperty(value = "SPU编码")
    private String spuCode;

    @ApiModelProperty(value = "销售价")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "初始销量")
    private Integer virtualSales;

    @ApiModelProperty(value = "实际销量")
    private Integer realSales;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "产品状态")
    private Integer goodsStatus;

    @ApiModelProperty(value = "是否热门产品")
    private Integer isHot;

    @ApiModelProperty(value = "是否分销产品")
    private Integer isDis;

    @ApiModelProperty(value = "poster")
    private String poster;
}
