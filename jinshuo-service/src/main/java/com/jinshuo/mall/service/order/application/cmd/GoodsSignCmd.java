package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname GoodsSignCmd
 * @Description 签名参数
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class GoodsSignCmd {

    @ApiModelProperty(value = "url")
    @NotNull(message = "url不能为空")
    private String url;

}
