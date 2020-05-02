package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetailId;
import com.jinshuo.mall.service.order.application.qry.OrderCountQry;
import com.jinshuo.mall.service.order.application.qry.OrderDetailQryDto;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname GoodsOrderAddressRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class GoodsOrderDetailRepo {


    @Autowired(required = false)
    private GoodsOrderDetailMapper mapper;

    /**
     * 获取id
     *
     * @return
     */
    public GoodsOrderDetailId nextId() {
        return new GoodsOrderDetailId(CommonSelfIdGenerator.generateId());
    }

    /**
     * @param goodsOrderId
     * @return
     */
    public List<GoodsOrderDetail> findByOrderId(GoodsOrderId goodsOrderId) {
        return mapper.findByOrderId(goodsOrderId);
    }

    /**
     * 保存收货地址
     *
     * @param goodsOrderDetail 产品信息
     */
    public void save(GoodsOrderDetail goodsOrderDetail) {
        mapper.save(goodsOrderDetail);
    }

    /**
     * 根据供应商id查询订单详情
     *
     * @param qry
     * @return
     */
    public List<OrderDetailQryDto> findBySupplierId(OrderCountQry qry) {
        return mapper.queryBySupplierId(qry);
    }

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    public GoodsOrderDetail findById(Long id) {
        return mapper.queryById(id);
    }
}
