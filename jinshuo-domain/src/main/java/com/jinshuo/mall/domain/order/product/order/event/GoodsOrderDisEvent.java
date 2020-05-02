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
 * 订单佣金计算
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
public class GoodsOrderDisEvent extends BaseDomainEvent {
    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 会员编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memId;

    /**
     * 订单编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    /**
     * 0->已完成的订单 直接增加到可提现金额  1->尚未完成的订单增加到待结算金额
     */
    private Integer settleStatus;

    /**
     * 订单时间
     */
    private Date orderTime;


   /* @ApiModelProperty(value = "订单信息")
    private List<OrderDetailDto> orderDetailCmds;*/
}
