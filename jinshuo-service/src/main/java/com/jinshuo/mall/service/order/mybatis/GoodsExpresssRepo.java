package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpress;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpressId;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsExpressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 快递信息
 * @Classname GoodsExpresssRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class GoodsExpresssRepo {


    @Autowired(required = false)
    private GoodsExpressMapper mapper;

    @Autowired(required = false)
    private GoodsOrderRepo goodsOrderMapper;


    /**
     * 获取id
     * @return
     */
    public GoodsOrderExpressId nextId() {
        return new GoodsOrderExpressId(CommonSelfIdGenerator.generateId());
    }


    /**
     * 保存
     * @param goodsOrderExpress
     */
    public void save(GoodsOrderExpress goodsOrderExpress) {
        mapper.save(goodsOrderExpress);
        //更新订单状态
        goodsOrderMapper.update(goodsOrderExpress.getGoodsOrder());
    }

    /**
     * 根据id条件查询订单
     * @param goodsOrderId
     * @return
     */
    public GoodsOrderExpress findGoodsExpressById(GoodsOrderId goodsOrderId){
        GoodsOrderExpress goodsOrderExpress = mapper.findGoodsExpressById(goodsOrderId);
        return goodsOrderExpress;
    }
}
