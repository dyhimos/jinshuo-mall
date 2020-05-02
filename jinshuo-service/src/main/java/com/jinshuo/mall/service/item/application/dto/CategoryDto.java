package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/7/15.
 */
@Data
public class CategoryDto {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "父ID")
    private String pid;

    @ApiModelProperty(value = "父名称")
    private String pname;

    @ApiModelProperty(value = "是否叶子接口")
    private Integer leaf;

    @ApiModelProperty(value = "所在层级")
    private Integer level;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类目类型")
    private Integer cateType;

    //private Integer orderSeq;

    @ApiModelProperty(value = "展示图片")
    private String pictureUrl;

    //private String backCategories;

    //private Integer needAudit;

    @ApiModelProperty(value = "子目类集合")
    private List<CategoryDto> children;

    @ApiModelProperty(value = "是否展示 0展示 1不展示")
    private Integer isShow;

    @ApiModelProperty(value = "排序")
    private Integer orderSeq;
}
