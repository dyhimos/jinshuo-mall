package com.jinshuo.mall.domain.order.product.order.event;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.core.event.BaseDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 订单发送虚拟物品核销码模板事件
 *
 * @Classname VerificationWxTemplateEvent
 * @Description TODO
 * @Date 2019/6/28 21:03
 * @Created by dongyh
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class VerificationWxTemplateEvent extends BaseDomainEvent {
    /**
     * 消息类型 1：订单支付成功 2：实体物品发送快递 3：虚拟产品发送核销码
     */
    public String type = "3";
    /**
     * 接收者openid
     */
    public String touser;

    /**
     * 店铺id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long shopId;

    /**
     * 模板id
     */
    public String templateId;

    /**
     * 跳转链接
     */
    public String url;

    /**
     * 订单id
     */
    public String orderId;

    /**
     * 产品名称
     */
    public String goodsName;

    /**
     * 订单使用码
     */
    public String verifySn;

}
