package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.mall.domain.order.product.orderrefund.GoodsOrderRefund;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderRefundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/12/23.
 */
@Repository
public class GoodsOrderRefundRepo {

    @Autowired(required = false)
    private GoodsOrderRefundMapper goodsOrderRefundMapper;

    public int create(GoodsOrderRefund goodsOrderRefund) {
        return goodsOrderRefundMapper.create(goodsOrderRefund);
    }

    public List<GoodsOrderRefund> queryMyOrder(Long memberId) {
        return goodsOrderRefundMapper.queryMyOrder(memberId);
    }

    public GoodsOrderRefund queryById(Long id) {
        return goodsOrderRefundMapper.queryById(id);
    }

    public int review(GoodsOrderRefund goodsOrderRefund) {
        return goodsOrderRefundMapper.review(goodsOrderRefund);
    }

    public int refund(GoodsOrderRefund goodsOrderRefund) {
        return goodsOrderRefundMapper.refund(goodsOrderRefund);
    }
}
