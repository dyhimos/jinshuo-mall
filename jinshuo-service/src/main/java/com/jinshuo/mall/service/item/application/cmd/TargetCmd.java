package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by 19458 on 2019/8/14.
 */
@Data
@Builder
public class TargetCmd {
    @ApiModelProperty(value = "skuId")
    private Long targetId;

    @ApiModelProperty(value = "数量")
    private Integer count;
}
