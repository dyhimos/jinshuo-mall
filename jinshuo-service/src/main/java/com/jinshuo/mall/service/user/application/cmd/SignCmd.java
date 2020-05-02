package com.jinshuo.mall.service.user.application.cmd;

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
public class SignCmd {

    @ApiModelProperty(value = "url")
    @NotNull(message = "url不能为空")
    private String url;

    @ApiModelProperty(value = "shopId")
    @NotNull(message = "店铺id不能为空")
    private Long shopId;

}
