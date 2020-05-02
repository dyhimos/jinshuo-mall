package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/18.
 */
@Data
public class SpecOptionIdCmd implements Serializable {

    @NotNull(message = "规格ID不能为空！")
    @ApiModelProperty(value = "规格ID")
    private Long id;

    //@NotNull(message = "规格排序不能为空！")
    @ApiModelProperty(value = "规格排序")
    private Integer sort;
}
