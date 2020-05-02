package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/8/1.
 */
@Data
public class CollectCmd {

    @ApiModelProperty(value = "收藏产品类型 1:spu")
    @NotNull(message = "收藏产品类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "收藏产品")
    @NotNull(message = "收藏产品不能为空")
    private Long targetId;
}
