package com.jinshuo.mall.service.user.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Classname SpuDto
 * @Description TODO
 * @Date 2019/6/28 9:29
 * @Created by dongyh
 */
@Data
@Accessors(chain = true)
public class UserCollectSpuDto implements Serializable {

    @ApiModelProperty(value = "收藏id")
    private String id;

    @ApiModelProperty(value = "spuId")
    private String spuId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品简述")
    private String sketch;

    @ApiModelProperty(value = "商品标签")
    private String tag;

    @ApiModelProperty(value = "封面图")
    private String pictureUrl;

    @ApiModelProperty(value = "SPU编码")
    private String spuCode;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "销售价")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "产品状态")
    private Integer goodsStatus;
}
