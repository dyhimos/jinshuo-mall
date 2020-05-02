package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.orderSimple.GoodsOrderSimple;
import com.jinshuo.mall.domain.order.product.orderSimple.GoodsOrderSimpleId;
import com.jinshuo.mall.service.order.application.qry.GoodsSimpleQry;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsOrderSimpleMapper;
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
public class GoodsOrderSimpleRepo {


    @Autowired(required = false)
    private GoodsOrderSimpleMapper mapper;

    /**
     * 获取id
     *
     * @return
     */
    public GoodsOrderSimpleId nextId() {
        return new GoodsOrderSimpleId(CommonSelfIdGenerator.generateId());
    }


    /**
     * 查询寄样信息详情
     *
     * @param id
     * @return
     */
    public GoodsOrderSimple findById(Long id) {
        return mapper.findById(id);
    }

    /**
     * 查询寄样信息详情
     *
     * @param sampleNo
     * @return
     */
    public GoodsOrderSimple findBySampleNo(String sampleNo) {
        return mapper.findBySampleNo(sampleNo);
    }


    /**
     * 查询寄样信息列表
     *
     * @param goodsSimpleQry
     * @return
     */
    public List<GoodsOrderSimple> findList(GoodsSimpleQry goodsSimpleQry) {
        return mapper.findList(goodsSimpleQry);
    }


    /**
     * 保存收货地址
     *
     * @param goodsOrderSimple 寄样信息
     */
    public void save(GoodsOrderSimple goodsOrderSimple) {
        mapper.save(goodsOrderSimple);
    }

    /**
     * 更新寄样产品状态
     *
     * @param id
     * @param status
     */
    public void updateSimpleStatus(Long id, Integer status) {
        mapper.updateSimpleStatus(id, status);
    }

    /**
     * 更新快递信息
     *
     * @param goodsOrderSimple
     */
    public void upSampleOrderExpress(GoodsOrderSimple goodsOrderSimple) {
        mapper.upSampleOrderExpress(goodsOrderSimple);
    }

    /**
     * 删除寄样信息
     *
     * @param id
     */
    public void delete(Long id) {
        mapper.delete(id);
    }
}
