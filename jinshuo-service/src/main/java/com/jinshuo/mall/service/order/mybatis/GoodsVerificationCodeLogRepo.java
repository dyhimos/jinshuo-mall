package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCodeLog;
import com.jinshuo.mall.service.order.application.qry.LogQry;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsVerificationCodeLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname GoodsVerificationCodeLogRepo
 * @Description TODO
 * @Date 2019/11/16 20:07
 * @Created by dyh
 */
@Repository
public class GoodsVerificationCodeLogRepo {


    @Autowired(required = false)
    private GoodsVerificationCodeLogMapper mapper;

    /**
     * @param orderVerificationCodeLog
     */
    public int save(OrderVerificationCodeLog orderVerificationCodeLog) {
        return mapper.save(orderVerificationCodeLog);
    }


    /**
     * @return
     */
    public List<OrderVerificationCodeLog> findBySupplierId(Long supplierId) {
        return mapper.selectBySupplierId(supplierId);
    }

    /**
     * @return
     */
    public OrderVerificationCodeLog findById(Long id) {
        return mapper.selectById(id);
    }

    /**
     * 根据产品查询核销记录
     *
     * @return
     */
    public List<OrderVerificationCodeLog> findBySpuId(LogQry qry) {
        return mapper.selectBySpuId(qry);
    }

}
