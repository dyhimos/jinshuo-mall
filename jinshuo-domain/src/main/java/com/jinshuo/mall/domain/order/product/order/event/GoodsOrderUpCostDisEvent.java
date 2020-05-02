package com.jinshuo.mall.domain.order.product.order.event;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.core.event.BaseDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单支付完成更新用户支付金额 并查看是否满足更新为分销员的条件
 *
 * @Classname GoodsOrderUpCostDisEvent
 * @Description TODO
 * @Date 2019/6/28 21:03
 * @Created by dongyh
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class GoodsOrderUpCostDisEvent extends BaseDomainEvent {

    /**
     * 会员id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 消费总额
     */
    private BigDecimal consumeAmount;

    /**
     * 订单号
     */
    private String orderNo;


    /**
     * 最近消费时间
     */
    private Date consumeTime;
}
