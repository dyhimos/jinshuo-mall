package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/19.
 */
@Data
public class AlbumUpdateCmd implements Serializable {
    @NotNull(message = "商品ID不能为空！")
    @ApiModelProperty(value = "id")
    private Long id;

    @NotNull(message = "商品ID不能为空！")
    @ApiModelProperty(value = "spuId")
    private Long spuId;

    @NotNull(message = "商品小类不能为空！")
    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @NotNull(message = "URL不能为空！")
    @ApiModelProperty(value = "URL")
    private String url;

    //@NotNull(message = "排序不能为空！")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @NotNull(message = "类型不能为空！")
    @ApiModelProperty(value = "类型")
    private Integer type;
}
