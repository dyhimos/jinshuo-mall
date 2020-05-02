package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.order.GoodsOrder;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.service.order.application.dto.CountOrderDto;
import com.jinshuo.mall.service.order.application.dto.OrderStatusDto;
import com.jinshuo.mall.service.order.application.qry.GoodsOrderQry;
import com.jinshuo.mall.service.order.application.qry.ManagerOrderCountQry;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderAddressMapper;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderCouponMapper;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderDetailMapper;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Classname GoodsOrderRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class GoodsOrderRepo {


    @Autowired(required = false)
    private GoodsOrderMapper mapper;

    @Autowired(required = false)
    private GoodsOrderDetailMapper goodsOrderDetailMapper;

    @Autowired(required = false)
    private GoodsOrderAddressMapper goodsOrderAddressMapper;

    @Autowired(required = false)
    private GoodsOrderCouponMapper goodsOrderCouponMapper;

    /**
     * 获取id
     *
     * @return
     */
    public GoodsOrderId nextId() {
        return new GoodsOrderId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 保存
     *
     * @param goodsOrder
     */
    public void save(GoodsOrder goodsOrder) {
        mapper.save(goodsOrder);
        //产品信息
        goodsOrder.getGoodsOrderDetailList().forEach(goodsOrderDetail -> goodsOrderDetailMapper.save(goodsOrderDetail));

        if (goodsOrder.getGoodsOrderAddress() != null) {
            //地址信息
            goodsOrderAddressMapper.save(goodsOrder.getGoodsOrderAddress());
        }

        if (goodsOrder.getGoodsOrderCouponList().size() > 0) {
            //保存优惠券使用信息
            goodsOrder.getGoodsOrderCouponList().forEach(goodsOrderCoupon -> goodsOrderCouponMapper.save(goodsOrderCoupon));
        }
    }

    /**
     * 根据id条件查询订单
     *
     * @param goodsOrderId
     * @return
     */
    public GoodsOrder findGoodsOrderById(GoodsOrderId goodsOrderId) {
        GoodsOrder goodsOrder = mapper.findGoodsOrderById(goodsOrderId);
        return goodsOrder;
    }


    /**
     * 根据订单号查询订单
     *
     * @param goodsOrder
     * @return
     */
    public GoodsOrder findGoodsOrderByOrderNo(GoodsOrder goodsOrder) {
        return mapper.findGoodsOrderByOrderNo(goodsOrder);
    }


    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public List<GoodsOrder> queryGoodsOrderList(GoodsOrderQry query) {
        return mapper.queryGoodsOrder(query);
    }


    /**
     * 更新数据
     *
     * @param goodsOrder
     * @return
     */
    public void update(GoodsOrder goodsOrder) {
        mapper.update(goodsOrder);
    }

    public void finshed(List<Long> goodsOrderIdCmdList) {
        mapper.finshed(goodsOrderIdCmdList);
    }

    /**
     * 更新数据
     *
     * @param status
     * @return
     */
    public void updateOrderStatus(Long orderId, Integer status) {
        mapper.updateOrderStatus(orderId, status);
    }


    /**
     * 统计数据
     *
     * @param qry
     * @return
     */
    public List<GoodsOrder> countOrder(ManagerOrderCountQry qry) {
        return mapper.countOrder(qry);
    }

    /**
     * 统计数据
     *
     * @param shopId
     * @return
     */
    public List<OrderStatusDto> historyCount(Long shopId) {
        return mapper.historyCount(shopId);
    }


    /**
     * 统计查询对账单
     *
     * @param date
     * @param type
     * @return
     */
    public List<BigDecimal> countOrderAmount(Date date, Integer type) {
        return mapper.countOrderAmount(date, type);
    }


    /**
     * 按照日期分类汇总订单数量
     *
     * @return
     */
    public List<CountOrderDto> countOrderByDate() {
        return mapper.countOrderByDate();
    }
}
