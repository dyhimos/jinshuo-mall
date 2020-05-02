package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/7/17.
 */
@Data
public class SpecDto {

    @ApiModelProperty(value = "规格ID")
    private String specId;

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "类目ID")
    private String categoryId;

    @ApiModelProperty(value = "类目名称")
    private String categoryName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "规格参数集合")
    private List<SpecOptionDto> options;
}
