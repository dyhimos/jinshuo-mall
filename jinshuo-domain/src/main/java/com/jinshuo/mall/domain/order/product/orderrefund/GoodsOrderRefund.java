package com.jinshuo.mall.domain.order.product.orderrefund;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsOrderRefund extends IdentifiedEntity {

    private GoodsOrderRefundId goodsOrderRefundId;

    private Long memberId;

    private Long orderId;

    private Long orderDetailId;

    private Integer refundStatus;// 退款状态 1待审核 2已审核（待发货） 3 待打款 0已完成 4审核驳回

    private Long skuId;

    private Integer count;

    private BigDecimal amount;

    private String refundReason;

    private String refundRemarks;

    private String expressCompanyName;

    private String expressNo;

    private Date refundTime;

    private String refundDocuments;

    private Date reviewTime;

    private String reviewReason;

    public void init() {
        super.preInsert();
        this.refundStatus = OrderRefundStatusEnums.ORDER_STATUS_REVIEWING.code;
    }

    public void review(Integer refundStatus, String reviewReason) {
        this.reviewReason = reviewReason;
        this.refundStatus = refundStatus;
        this.reviewTime = new Date();
        this.updateDate = new Date();
    }

    public void refund(String refundDocuments) {
        this.refundDocuments = refundDocuments;
        this.refundTime = new Date();
        this.updateDate = new Date();
    }

}