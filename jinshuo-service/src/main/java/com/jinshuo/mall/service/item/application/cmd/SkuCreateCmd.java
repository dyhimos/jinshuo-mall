package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 19458 on 2019/7/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SkuCreateCmd implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @NotNull(message = "名称不能为空！")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(message = "图片资源不能为空！")
    @ApiModelProperty(value = "图片资源")
    private String pictureUrl;

    @NotNull(message = "销售价不能为空！")
    @ApiModelProperty(value = "销售价")
    private BigDecimal price;

    @NotNull(message = "市场价不能为空！")
    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @NotNull(message = "成本价不能为空！")
    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;

    @NotNull(message = "库存不能为空！")
    @ApiModelProperty(value = "库存")
    private Integer stock;

    @NotNull(message = "销量不能为空！")
    @ApiModelProperty(value = "销量")
    private Integer salesQuantity;

    @NotNull(message = "规格参数不能为空！")
    @ApiModelProperty(value = "规格参数")
    private List<SpecOptionIdCmd> optionIds;

    @NotNull(message = "sku编码不能为空！")
    @ApiModelProperty(value = "sku编码")
    private String skuCode;

    @NotNull(message = "条形码不能为空！")
    @ApiModelProperty(value = "条形码")
    private String barCode;
}
