package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/8/16.
 */
@Data
public class UpMemberPhoneCmd {

    @ApiModelProperty(value = "手机号码 ")
    @NotNull(message = "所在省不能为空")
    private String phone;

    @ApiModelProperty(value = "手机短信码")
    @NotNull(message = "手机短信码")
    private String code;
}
