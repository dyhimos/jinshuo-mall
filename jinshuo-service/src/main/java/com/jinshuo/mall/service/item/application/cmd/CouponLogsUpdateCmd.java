package com.jinshuo.mall.service.item.application.cmd;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponLogsUpdateCmd extends IdentifiedEntity {
    private Long id;
    private Long couponReceiveId; //优惠券码编号
    private Long orderId;//订单编号
    private BigDecimal orderOriginalAmount;//原订单金额
    private BigDecimal couponAmount;//优惠券的金额
    private BigDecimal orderFinalAmount;//抵扣优惠券之后的订单金额
    private Date useTime;//使用时间
}
