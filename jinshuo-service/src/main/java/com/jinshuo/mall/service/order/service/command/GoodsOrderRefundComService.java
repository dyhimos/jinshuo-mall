package com.jinshuo.mall.service.order.service.command;

import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.exception.order.OcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.product.order.GoodsOrder;
import com.jinshuo.mall.domain.order.product.order.OrderStatusEnums;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.domain.order.product.orderrefund.GoodsOrderRefund;
import com.jinshuo.mall.domain.order.product.orderrefund.GoodsOrderRefundId;
import com.jinshuo.mall.service.order.application.cmd.GoodsOrderRefundCmd;
import com.jinshuo.mall.service.order.application.cmd.GoodsReviewOrderCmd;
import com.jinshuo.mall.service.order.mybatis.GoodsOrderRefundRepo;
import com.jinshuo.mall.service.order.service.query.GoodsOrderDetailQueryService;
import com.jinshuo.mall.service.order.service.query.GoodsOrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 19458 on 2019/12/24.
 */
@Service
public class GoodsOrderRefundComService {

    @Autowired
    private GoodsOrderDetailQueryService goodsOrderDetailQueryService;

    @Autowired
    private GoodsOrderQueryService goodsOrderQueryService;

    @Autowired
    private GoodsOrderRefundRepo goodsOrderRefundRepo;

    @Autowired
    private GoodsOrderCmdService goodsOrderCmdService;


    /**
     * 发起退款
     *
     * @param cmd
     * @return
     * @throws Exception
     */
    @Transactional
    public GoodsOrderRefund applyRefund(GoodsOrderRefundCmd cmd) throws OcBizException {
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetailQueryService.getDetailById(cmd.getOrderDetailId());
        if (null == goodsOrderDetail) {
            throw new OcBizException(OcReturnCode.OC202005.getMsg());
        }
        GoodsOrder goodsOrder = goodsOrderQueryService.findOrderById(goodsOrderDetail.getGoodsOrderId());
        if (null == goodsOrder) {
            throw new OcBizException(OcReturnCode.OC202005.getMsg());
        }
        goodsOrder.checkRefundStatus();
        GoodsOrderRefund goodsOrderRefund = GoodsOrderRefund.builder()
                .goodsOrderRefundId(new GoodsOrderRefundId(CommonSelfIdGenerator.generateId()))
                .amount(goodsOrderDetail.getSubtotal())
                .count(goodsOrderDetail.getNumber())
                .refundReason(cmd.getRefundReason())
                .refundRemarks(cmd.getRefundRemarks())
                .orderDetailId(cmd.getOrderDetailId())
                .orderId(goodsOrder.getGoodsOrderId().getId())
                .skuId(goodsOrderDetail.getGoodsSkuId())
                .expressCompanyName(cmd.getExpressCompanyName())
                .expressNo(cmd.getExpressNo())
                .build();
        goodsOrderRefund.init();
        goodsOrderRefundRepo.create(goodsOrderRefund);
        goodsOrderCmdService.updateOrderStatus(goodsOrder.getGoodsOrderId().getId(), OrderStatusEnums.ORDER_STATUS_REFUNDAPPLY.code);
        return goodsOrderRefund;
    }

    /**
     * 审核申请单
     */
    public void reviewRefundOrder(GoodsReviewOrderCmd cmd) {
        GoodsOrderRefund goodsOrderRefund = goodsOrderRefundRepo.queryById(cmd.getId());
        goodsOrderRefund.review(cmd.getStatus(), cmd.getReviewReason());
        goodsOrderRefundRepo.review(goodsOrderRefund);
    }

    /**
     * 退款
     */
    public void refundOrder(GoodsReviewOrderCmd cmd) {
        GoodsOrderRefund goodsOrderRefund = goodsOrderRefundRepo.queryById(cmd.getId());
        goodsOrderRefund.refund(cmd.getRefundDocuments());
        goodsOrderRefundRepo.refund(goodsOrderRefund);
    }
}
