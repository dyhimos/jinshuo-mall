package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderCoupon.GoodsOrderCoupon;
import com.jinshuo.mall.domain.order.product.orderCoupon.GoodsOrderCouponId;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderCouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname GoodsOrderCouponRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class GoodsOrderCouponRepo {

    @Autowired(required = false)
    private GoodsOrderCouponMapper mapper;

    /**
     * 获取id
     * @return
     */
    public GoodsOrderCouponId nextId() {
        return new GoodsOrderCouponId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 保存
     * @param goodsOrderCoupon
     */
    public void save(GoodsOrderCoupon goodsOrderCoupon) {
        mapper.save(goodsOrderCoupon);
    }

    /**
     * 根据订单号查询订单使用优惠券列表
     * @param goodsOrderId
     * @return
     */
    public List<GoodsOrderCoupon> findByOrderId(GoodsOrderId goodsOrderId){
       return mapper.findByOrderId(goodsOrderId);
    }
}
