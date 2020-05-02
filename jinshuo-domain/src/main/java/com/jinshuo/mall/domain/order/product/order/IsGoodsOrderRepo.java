package com.jinshuo.mall.domain.order.product.order;

/**
 * @author dongyh
 * @Classname IsGoodsOrderRepo
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dongyh
 */
public interface IsGoodsOrderRepo {

    GoodsOrderId nextId();

    /**
     * 保存订单
     *
     * @param goodsOrder
     */
    void save(GoodsOrder goodsOrder);
}
