package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/22.
 */
@Data
public class BrandCreateCmd implements Serializable {

    @NotNull(message = "名称不能为空！")
    @ApiModelProperty(value = "名称")
    private String name;

    //@NotNull(message = "图片链接不能为空！")
    @ApiModelProperty(value = "图片链接")
    private String pictureUrl;


    //@NotNull(message = "排序不能为空！")
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
