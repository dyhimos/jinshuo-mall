package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
public class WriteOffVerificationCodeCmd {

    @ApiModelProperty(value = "验证码")
    private String verifySn;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;
}
