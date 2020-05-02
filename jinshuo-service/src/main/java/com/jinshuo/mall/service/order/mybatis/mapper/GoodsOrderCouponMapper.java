package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderCoupon.GoodsOrderCoupon;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname GoodsOrderCouponMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsOrderCouponMapper {

    /**
     * 根据订单id查询优惠券使用信息
     *
     * @param goodsOrderId
     * @return
     */
    @Results(
            id = "goodsOrderCoupon",
            value = {
                    @Result(property = "goodsOrderCouponId.id", column = "id"),
                    @Result(property = "goodsOrderId.id", column = "order_id"),
                    @Result(property = "couponReceiveId", column = "coupon_receive_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "couponAmount", column = "coupon_amount"),
                    @Result(property = "subtotal", column = "subtotal")
            }
    )
    @Select("SELECT * FROM goods_order_coupon WHERE order_id=#{id}")
    List<GoodsOrderCoupon> findByOrderId(GoodsOrderId goodsOrderId);

    /**
     * 保存订单优惠券使用信息
     *
     * @param goodsOrderCoupon
     */
    @InsertProvider(type = DynamicSql.class, method = "createGoodsOrderCouponSql")
    void save(GoodsOrderCoupon goodsOrderCoupon);
}
