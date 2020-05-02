package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCodeCmd {

    @ApiModelProperty(value = "验证码")
    private String verifySn;

    @ApiModelProperty(value = "订单详情id")
    private Long orderDetailId;
}
