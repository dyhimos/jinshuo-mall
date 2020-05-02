package com.jinshuo.mall.service.order.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCode;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCodeLog;
import com.jinshuo.mall.service.order.mybatis.GoodsVerificationCodeLogRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/11/21.
 */
@Slf4j
@Service
public class OrderVerificationCodeLogComService {

    @Autowired
    private GoodsVerificationCodeLogRepo repo;

    public int recording(OrderVerificationCode orderVerificationCode, GoodsOrderDetail goodsOrderDetail, Integer errCode, String errMsg) throws OcBizException {
        log.info(" -- 记录核销记录，输入参数：orderVerificationCode：" + JSONObject.toJSONString(orderVerificationCode)
                + ";goodsOrderDetail: " + goodsOrderDetail + "; errCode:" + errCode + "; errMsg:" + errMsg);
        OrderVerificationCodeLog log = OrderVerificationCodeLog.builder()
                .verifySn(orderVerificationCode.getVerifySn())
                .userAccountId(UserIdUtils.getUserId())
                .errCode(errCode)
                .errMsg(errMsg)
                .name("")
                .build();
        if (null != orderVerificationCode.getOrderVerificationCodeId() && null != orderVerificationCode.getOrderVerificationCodeId().getId()) {
            log.setVerificationId(orderVerificationCode.getOrderVerificationCodeId().getId());
        }
        if (null != goodsOrderDetail) {
            log.setGoodsOrderId(goodsOrderDetail.getGoodsOrderId().getId());
            log.setGoodsOrderDetailId(goodsOrderDetail.getGoodsOrderDetailId().getId());
            if (null != goodsOrderDetail.getSupplierId()) {
                log.setSupplierId(goodsOrderDetail.getSupplierId());
            }
        }
        log.insert();
        return repo.save(log);
    }

}
