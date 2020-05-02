package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by 19458 on 2019/7/17.
 */
@Data
public class SpecUpdateCmd implements Serializable {

    //@NotNull(message = "规格ID不能为空！")
    @ApiModelProperty(value = "规格ID")
    private Long id;

    @NotNull(message = "规格ID不能为空！")
    @ApiModelProperty(value = "规格ID")
    private Long specId;

    @NotNull(message = "规格名称不能为空！")
    @ApiModelProperty(value = "规格名称")
    private String name;

    //@NotNull(message = "产品类目不能为空！")
    @ApiModelProperty(value = "产品类目")
    private Long categoryId;

    //@NotNull(message = "排序不能为空！")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @NotNull(message = "规格详情不能为空！")
    @ApiModelProperty(value = "规格详情")
    private List<SpecOptionCreateCmd> options;

    @ApiModelProperty(value = "shopId")
    private Long shopId;
}
