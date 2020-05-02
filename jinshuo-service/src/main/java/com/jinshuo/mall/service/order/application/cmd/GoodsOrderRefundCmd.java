package com.jinshuo.mall.service.order.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsOrderRefundCmd  {

    private Long memberId;

    private Long orderDetailId;

    private Integer count;

    private String refundReason;

    private String refundRemarks;

    private String expressCompanyName;

    private String expressNo;

}