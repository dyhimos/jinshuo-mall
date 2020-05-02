package com.jinshuo.mall.domain.order.product.order.event;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.core.event.BaseDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 订单实物发货模板事件
 *
 * @Classname ExpressWxTemplateEvent
 * @Description TODO
 * @Date 2019/6/28 21:03
 * @Created by dongyh
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class ExpressWxTemplateEvent extends BaseDomainEvent {
    /**
     * 消息类型 1：订单支付成功 2：实体物品发送快递 3：虚拟产品发送核销码
     */
    public String type = "2";
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
     * 模板Id
     */
    public String templateId;
    /**
     * 订单id
     */
    public String orderId;

    /**
     * 跳转链接
     */
    public String url;

    /**
     * 产品名称
     */
    public String goodsName;

    /**
     * 快递公司名称
     */
    public String expressCompanyName;

    /**
     * 收货地址
     */
    public String address;

    /**
     * 物流单号
     */
    public String expressNo;

    /**
     * 发货时间
     */
    public Date expressTime;
}
