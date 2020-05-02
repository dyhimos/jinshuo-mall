package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/5.
 */
@Data
public class CategoryUpdateCmd implements Serializable {

    @NotNull(message = "id不能为空！")
    @ApiModelProperty(value = "ID")
    private Long id;

    @NotNull(message = "是否叶子节点不能为空！")
    @ApiModelProperty(value = "是否叶子节点")
    private Integer leaf;

    private Long pid;

    @NotNull(message = "层级不能为空！")
    @ApiModelProperty(value = "层级")
    private Integer level;

    @NotNull(message = "名称不能为空！")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(message = "类目类型不能为空！")
    @ApiModelProperty(value = "类目类型")
    private Integer cateType;

    //@NotNull(message = "排序序列不能为空！")
    @ApiModelProperty(value = "排序序列")
    private Integer orderSeq;

    @NotNull(message = "类目图片不能为空！")
    @ApiModelProperty(value = "类目图片")
    private String pictureUrl;

    private String backDategories;

    //@NotNull(message = "是否需要审核不能为空！")
    @ApiModelProperty(value = "是否需要审核")
    private Integer needAudit;

    //@NotNull(message = "店铺id不能为空！")
    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "是否展示0:展示 1:不展示")
    private Integer isShow;
}
