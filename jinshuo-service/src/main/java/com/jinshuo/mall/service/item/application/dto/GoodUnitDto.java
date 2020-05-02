package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/7/12.
 */
@Data
public class GoodUnitDto {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "单位名称")
    private String name;

}
