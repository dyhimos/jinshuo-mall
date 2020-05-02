package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCode;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCodeId;
import com.jinshuo.mall.service.order.mybatis.mapper.GoodsVerificationCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单验证码
 *
 * @Classname GoodsVerificationCodeRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class GoodsVerificationCodeRepo {


    @Autowired(required = false)
    private GoodsVerificationCodeMapper mapper;

    /**
     * 获取id
     *
     * @return
     */
    public OrderVerificationCodeId nextId() {
        return new OrderVerificationCodeId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 保存 验证码信息
     *
     * @param orderVerificationCode
     */
    public void save(OrderVerificationCode orderVerificationCode) {
        mapper.save(orderVerificationCode);
    }

    /**
     * 根据验证码查询码信息
     *
     * @param verifySn
     */
    public OrderVerificationCode findByverifySn(String verifySn) {
        return mapper.queryByverifySn(verifySn);
    }

    /**
     * 使用卷码
     *
     * @param orderVerificationCodeId
     */
    public int useVerificationCode(Long orderVerificationCodeId) {
        return mapper.useVerificationCode(orderVerificationCodeId);
    }

    /**
     * 根据验证码查询码信息
     *
     * @param orderId
     */
    public List<OrderVerificationCode> findOrderId(Long orderId) {
        return mapper.queryOrderId(orderId);
    }
}
