package com.jinshuo.mall.service.social.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/12/26.
 */
@Data
public class TopicOperateCmd {

    @NotNull(message = "点赞类型不能为空！")
    @ApiModelProperty(value = "点赞类型 0点赞帖子 1点赞评论")
    private Integer operateType;

    @NotNull(message = "ID不能为空！")
    @ApiModelProperty(value = "ID")
    private Long aimsId;
}
