package com.jinshuo.mall.domain.order.product.orderCoupon;

import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author dongyh
 * @Classname GoodsOrderCoupon
 * @Description 订单优惠券表
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsOrderCoupon {
    /**
     * 订单优惠券使用Id
     */
    private GoodsOrderCouponId goodsOrderCouponId;

    /**
     * 订单ID
     */
    private GoodsOrderId goodsOrderId;

    /**
     * 优惠券接收ID
     */
    private Long couponReceiveId;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券面值
     */
    private BigDecimal couponAmount;
}
