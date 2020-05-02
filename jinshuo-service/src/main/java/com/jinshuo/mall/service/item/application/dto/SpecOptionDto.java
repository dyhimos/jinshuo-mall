package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/7/17.
 */
@Data
public class SpecOptionDto {

    @ApiModelProperty(value = "id(同规格参数ID)")
    private String Id;

    @ApiModelProperty(value = "规格参数ID")
    private String specOptionId;

    @ApiModelProperty(value = "规格参数名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
