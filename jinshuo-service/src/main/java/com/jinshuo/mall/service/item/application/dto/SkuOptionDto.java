package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/19.
 */
@Data
@Builder
public class SkuOptionDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "规格ID")
    private String specId;

    @ApiModelProperty(value = "规格名称（颜色）")
    private String specName;

    @ApiModelProperty(value = "规格参数ID")
    private String optionId;

    @ApiModelProperty(value = "规格参数名称（红色）")
    private String optionName;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
