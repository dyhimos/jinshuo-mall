package com.jinshuo.mall.service.order.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/8/14.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsReviewOrderCmd {
    private Long id;
    private Integer status;
    private String reviewReason;
    private String refundDocuments;
}
