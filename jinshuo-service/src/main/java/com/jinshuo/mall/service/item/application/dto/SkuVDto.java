package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 19458 on 2019/7/19.
 */
@Data
public class SkuVDto implements Serializable {

    @ApiModelProperty(value = "skuId")
    private String id;

    @ApiModelProperty(value = "spuId")
    private String spuId;

    @ApiModelProperty(value = "sku名称")
    private String name;

    @ApiModelProperty(value = "主图")
    private String pictureUrl;

    @ApiModelProperty(value = "销售价")
    private BigDecimal price;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "销量")
    private Integer salesQuantity;

    @ApiModelProperty(value = "规格集合")
    private List<SkuOptionDto> specOptionDtos;
}
