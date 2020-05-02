package com.jinshuo.mall.domain.order.product.orderVerificationCode;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetailId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

/**
 * @author dongyh
 * @Classname OrderVerificationCode
 * @Description 订单验证码
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVerificationCode extends IdentifiedEntity {
    /**
     * 编号
     */
    private OrderVerificationCodeId orderVerificationCodeId;

    /**
     * 订单编号
     */
    private GoodsOrderId goodsOrderId;

    /**
     * 订单详情id
     */
    private GoodsOrderDetailId goodsOrderDetailId;

    /**
     * 验证码
     */
    private String verifySn;

    /**
     * 二维码
     */
    private BigInteger qrCode;

    /**
     * 是否使用 0已使用 1未使用
     */
    private Integer isUse;

    private List<OrderVerificationCodeLog> verificationLogList;
}
