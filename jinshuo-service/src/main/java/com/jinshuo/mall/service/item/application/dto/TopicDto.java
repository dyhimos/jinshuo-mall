package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/9/29.
 */
@Data
public class TopicDto {

    private String id;

    @ApiModelProperty(value = "活动类型 0活动 1主题")
    @NotNull(message = "活动类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "活动父代码")
    //@NotNull(message = "活动父代码不能为空")
    private String pid;

    @ApiModelProperty(value = "活动代码")
    @NotNull(message = "活动代码不能为空")
    private String code;

    @ApiModelProperty(value = "活动名称")
    @NotNull(message = "活动名称不能为空")
    private String name;

    @ApiModelProperty(value = "活动开始时间")
    @NotNull(message = "活动开始时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    @NotNull(message = "活动结束时间不能为空")
    private Date endTime;

    @ApiModelProperty(value = "活动状态")
    @NotNull(message = "活动状态不能为空")
    private Integer topicStatus;

    @ApiModelProperty(value = "活动描述")
    @NotNull(message = "活动描述不能为空")
    private String topicDesc;

    @ApiModelProperty(value = "主图标")
    //@NotNull(message = "主图标不能为空")
    private String mainPicture;

    @ApiModelProperty(value = "小图标")
    //@NotNull(message = "小图标不能为空")
    private String signPicture;

    @ApiModelProperty(value = "海报")
    //@NotNull(message = "海报不能为空")
    private String posterPicture;

    @ApiModelProperty(value = "活动背景颜色")
    //@NotNull(message = "活动背景颜色不能为空")
    private String color;

    @ApiModelProperty(value = "店铺id")
    @NotNull(message = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "showType")
    private Integer showType;

    @ApiModelProperty(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "0文字 1：图标")
    private Integer headingShowFlag; //

    @ApiModelProperty(value = "字体颜色")
    private String headingColor; //

    private List<TopicDto> list;
}
