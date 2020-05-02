package com.jinshuo.mall.service.order.service.query;

import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.service.order.mybatis.GoodsOrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/12/24.
 */
@Service
public class GoodsOrderDetailQueryService {

    @Autowired
    private GoodsOrderDetailRepo goodsOrderDetailRepo;

    public GoodsOrderDetail getDetailById(Long id) {
        return goodsOrderDetailRepo.findById(id);
    }
}
