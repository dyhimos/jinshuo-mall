package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class CouponLogsCreateCmd{

    @ApiModelProperty(value = "优惠券码编号")
    private Long couponReceiveId; //优惠券码编号

    //@NotNull(message = "订单编号不能为空！")
    @ApiModelProperty(value = "订单编号")
    private Long orderId;//订单编号

    @ApiModelProperty(value = "订单金额-后台计算")
    private BigDecimal orderOriginalAmount;//原订单金额

    @NotNull(message = "会员id不能为空！")
    @ApiModelProperty(value = "会员id")
    private Long memId; //

    @NotNull(message = "产品信息不能为空！")
    private List<TargetCmd> targetCmds;
}
