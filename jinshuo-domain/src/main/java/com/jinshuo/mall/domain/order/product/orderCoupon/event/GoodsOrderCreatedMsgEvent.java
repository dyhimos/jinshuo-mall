package com.jinshuo.mall.domain.order.product.orderCoupon.event;

import com.jinshuo.core.event.BaseDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 订单创建触发短信事件
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
public class GoodsOrderCreatedMsgEvent extends BaseDomainEvent {
    /**
     * 接受者姓名
     */
    public String name;
    /**
     * 手机号码
     */
    public String mobile;
    /**
     * 短信内容
     */
    public String content;
}
