package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddressId;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Classname GoodsOrderAddressRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class GoodsOrderAddressRepo {


    @Autowired(required = false)
    private GoodsOrderAddressMapper mapper;

    /**
     * 获取id
     *
     * @return
     */
    public GoodsOrderAddressId nextId() {
        return new GoodsOrderAddressId(CommonSelfIdGenerator.generateId());
    }

    public GoodsOrderAddress findByOrderId(GoodsOrderId goodsOrderId) {
        return mapper.findByOrderId(goodsOrderId);
    }

    /**
     * 保存收货地址
     *
     * @param goodsOrderAddress 地址
     */
    public void save(GoodsOrderAddress goodsOrderAddress) {
        mapper.save(goodsOrderAddress);
    }
}
