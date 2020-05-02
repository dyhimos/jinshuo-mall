package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/23.
 */
@Data
public class CommId implements Serializable {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID")
    private Long id;
}
