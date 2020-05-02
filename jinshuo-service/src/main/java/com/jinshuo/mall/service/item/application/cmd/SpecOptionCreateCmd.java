package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/17.
 */
@Data
public class SpecOptionCreateCmd implements Serializable {

    @NotNull(message = "规格参数名称不能为空！")
    @ApiModelProperty(value = "规格参数")
    private String name;

    //@NotNull(message = "排序不能为空！")
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
