package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/22.
 */
@Data
public class TypeUpdateCmd implements Serializable {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID不能为空")
    private Long id;

    @ApiModelProperty(value = "类型名称")
    @NotNull(message = "类型名称不能为空")
    private String name;

    /*@ApiModelProperty(value = "绑定商品数量")
    @NotNull(message = "绑定商品数量不能为空!")
    private Integer goodsCount;*/

    @ApiModelProperty(value = "排序")
    //@NotNull(message = "排序不能为空!")
    private Integer sort;

}
