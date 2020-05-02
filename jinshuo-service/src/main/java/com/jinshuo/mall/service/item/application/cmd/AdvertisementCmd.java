package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by 19458 on 2019/9/10.
 */
@Data
public class AdvertisementCmd  {
    private Long id;

    @NotNull(message = "广告位id不能为空！")
    @ApiModelProperty(value = "广告位id")
    private Long adPositionId;

    @NotNull(message = "标题不能为空！")
    @ApiModelProperty(value = "标题")
    private String titile;

    @NotNull(message = "图片不能为空！")
    @ApiModelProperty(value = "图片")
    private String image;

    @NotNull(message = "url不能为空！")
    @ApiModelProperty(value = "url")
    private String url ;

    @NotNull(message = "排序不能为空！")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    //@NotNull(message = "是否可用不能为空！")
    @ApiModelProperty(value = "是否可用 0->可用 1->不可用")
    private Integer isEnabled;//是否可用 0->可用 1->不可用

    @NotNull(message = "地区不能为空！")
    @ApiModelProperty(value = "地区s")
    private List<String> areaNames;
}
