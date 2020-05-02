package com.jinshuo.mall.service.order.service.query;

import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import com.jinshuo.mall.service.order.mybatis.GoodsOrderAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dongyh
 * @Classname GoodsOrderAddressQueryService
 * @Description TODO 订单地址查询
 * @Date 2019/6/16 19:54
 * @Created by dongyh
 */

@Service
public class GoodsOrderAddressQueryService {

    @Autowired
    private GoodsOrderAddressRepo goodsOrderAddressRepo;

    /**
     * 根据订单id查询收货信息
     *
     * @return
     */
    public GoodsOrderAddress findByOrderId(GoodsOrderId goodsOrderId) {
        return goodsOrderAddressRepo.findByOrderId(goodsOrderId);
    }
}
