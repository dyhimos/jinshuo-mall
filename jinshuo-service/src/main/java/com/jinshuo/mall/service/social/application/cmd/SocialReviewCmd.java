package com.jinshuo.mall.service.social.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SocialReviewCmd {

    @NotNull(message = "审核状态不能为空！")
    @ApiModelProperty(value = "审核状态 0通过 2不通过")
    private Integer auditStatus;

    @NotNull(message = "审核类型不能为空！")
    @ApiModelProperty(value = "审核类型 0审核帖子 1审核评论 2审核回复")
    private Integer operateType;

    @NotNull(message = "ID不能为空！")
    @ApiModelProperty(value = "ID")
    private Long aimsId;
}