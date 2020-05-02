package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVerificationCodeCmd {


    @ApiModelProperty(value = "订单编号")
    private Long orderId;


    /**
     * 验证码list
     */
    List<VerificationCodeCmd> verificationCodeList;
}
