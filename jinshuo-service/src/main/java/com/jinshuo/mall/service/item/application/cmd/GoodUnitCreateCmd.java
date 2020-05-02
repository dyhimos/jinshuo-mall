package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/9.
 */
@Data
public class GoodUnitCreateCmd implements Serializable {

    @NotNull(message = "单位名称不能为空")
    @Size(min = 1, max = 10, message = "单位名称字符数为[1-20]")
    @ApiModelProperty(value = "单位名称")
    private String name;

    /*@NotNull(message = "单位状态不能为空")
    private String unitStatus;*/
}
