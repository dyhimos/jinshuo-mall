package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/9/25.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureCmd {

    @ApiModelProperty(value = "ID")
    private Long id;

    @NotNull(message = "名称不能为空！")
    @ApiModelProperty(value = "名称")
    private String name;

    //@NotNull(message = "排序字段不能为空！")
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @NotNull(message = "shopId不能为空！")
    @ApiModelProperty(value = "shopId")
    private Long shopId;
}
