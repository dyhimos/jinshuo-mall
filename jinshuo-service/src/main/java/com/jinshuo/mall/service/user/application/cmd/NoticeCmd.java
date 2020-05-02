package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NoticeCmd {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "附件")
    private String attachment;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "是否首页显示")
    private Integer isShow;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "有效期开始时间")
    private Date startTime;

    @ApiModelProperty(value = "有效期结束时间")
    private Date endTime;
}