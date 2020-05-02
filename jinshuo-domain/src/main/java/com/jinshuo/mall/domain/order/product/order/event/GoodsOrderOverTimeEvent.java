package com.jinshuo.mall.domain.order.product.order.event;

import com.jinshuo.core.event.BaseDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 订单超时事件
 *
 * @Classname GoodsOrderCreatedMsgEvent
 * @Description TODO
 * @Date 2019/6/28 21:03
 * @Created by dongyh
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class GoodsOrderOverTimeEvent extends BaseDomainEvent {

    /**
     * 超时事件
     */
    public final String EXPIREDTIME = String.valueOf(30 * 60 * 1000);
    /**
     * 订单ID
     */
    public String orderId;


}
