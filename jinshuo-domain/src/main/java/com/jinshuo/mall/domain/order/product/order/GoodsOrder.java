package com.jinshuo.mall.domain.order.product.order;

import com.jinshuo.core.exception.JsException;
import com.jinshuo.core.exception.JsReturnCode;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.model.enums.Status;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import com.jinshuo.mall.domain.order.product.orderCoupon.GoodsOrderCoupon;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author dongyh
 * @Classname GoodsOrder
 * @Description 订单
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsOrder extends IdentifiedEntity {
    /**
     * 订单id
     */
    private GoodsOrderId goodsOrderId;

    /**
     * 订单流水号
     */
    private String orderNo;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 客户编号
     */
    private Long memberId;

    /**
     * 会员名字
     */
    private String memberName;

    /**
     * 商户编号
     */
    private Long supplierId;

    /**
     * 商户名称
     */
    private String supplierName;

    /**
     * 订单状态
     */
    private OrderStatusEnums orderStatus;


    /**
     * 用户售后状态
     */
    private Integer afterStatus;

    /**
     * 商品数
     */
    private Integer goodsCount;

    /**
     * 商品总价
     */
    private BigDecimal goodsAmountTotal;

    /**
     * 运费金额
     */
    private BigDecimal logisticsFee;


    /**
     * 优惠金额
     */
    private BigDecimal couponAmount;

    /**
     * 实际付款金额
     */
    private BigDecimal orderAmountTotal;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 支付渠道
     */
    private PayChannelEnums payChannel;

    /**
     * 订单支付单号
     */
    private String outTradeNo;

    /**
     * 第三方支付流水号
     */
    private String escrowTradeNo;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 订单结算状态
     */
    private SettlementStatusEnums settlementStatus;

    /**
     * 订单结算时间
     */
    private Date orderSettlementTime;

    /**
     * 是否是积分产品
     */
    private Integer isIntegral;

    /**
     * 系统备注
     */
    private String systemRemarks;

    /**
     * 活动订单类型 0：普通订单 1：秒杀订单 2：拼团订单
     */
    private Integer orderClass;

    /**
     * 外部交易单号
     */
    private String outOrderNo;

    /**
     * 订单完成时间
     */
    private Date finshDate;

    /**
     * 自动取消（0：是 1：否）
     */
    private String autoCancel;

    /**
     * 是否支付  0已支付  1未支付
     */
    public OrderPayStatusEnums payStatus;

    /**
     * 产品list
     */
    public List<GoodsOrderDetail> goodsOrderDetailList;

    /**
     * 优惠券使用列表
     */
    public List<GoodsOrderCoupon> goodsOrderCouponList;

    /**
     * 收货地址信息
     */
    public GoodsOrderAddress goodsOrderAddress;

    /**
     * 快递信息
     */
    public GoodsOrderExpress goodsOrderExpress;

    /*protected static void validateSkus(GoodsOrder goodsOrder){
        goodsOrder.assertArgumentTrue(spu.specDefineTuple.cartesianProductEquals(skuGroups.keySet()), "与规格定义中的笛卡尔积不相符");
    }*/

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    public GoodsOrder cancelOrder(Long id, String autoCancel) {
        this.setGoodsOrderId(new GoodsOrderId((id)));
        this.autoCancel = autoCancel;
        updateStatus(id, OrderStatusEnums.ORDER_STATUS_CANCELTRANSACTION.code);
        return this;
    }


    /**
     * 完成订单
     *
     * @param id
     * @return
     */
    public GoodsOrder finshOrder(Long id) {
        this.setGoodsOrderId(new GoodsOrderId((id)));
        //更新为已完成
        updateStatus(id, OrderStatusEnums.ORDER_STATUS_FINISHED.code);
        return this;
    }

    /**
     * 更新订单
     *
     * @param id
     * @return
     */
    public GoodsOrder updateStatus(Long id, Integer status) {
        this.setGoodsOrderId(new GoodsOrderId((id)));
        this.orderStatus = OrderStatusEnums.getEnumByCode(status);
        this.updateDate = new Date();
        return this;
    }

    /**
     * 支付之后更新订单
     */
    public GoodsOrder afterPayGoodsOrder(Long id, Integer status, Integer payChannel, String outTradeNo, String transactionId) {
        //支付时间
        this.payTime = new Date();
        //支付单号
        this.outTradeNo = outTradeNo;
        //支付渠道
        this.payChannel = PayChannelEnums.getEnumByCode(payChannel);
        //微信支付单号
        this.setEscrowTradeNo(transactionId);
        this.updateStatus(id, status);
        return this;
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    public GoodsOrder delete(Long id) {
        this.setGoodsOrderId(new GoodsOrderId((id)));
        this.updateDate = new Date();
        this.status = Status.DELETE;
        return this;
    }

    /**
     * 更新订单备注
     *
     * @param id
     * @return
     */
    public GoodsOrder updateSysRemarks(Long id, String systemRemarks) {
        this.setGoodsOrderId(new GoodsOrderId((id)));
        this.updateDate = new Date();
        if (StringUtils.isNotBlank(systemRemarks)) {
            this.systemRemarks = systemRemarks;
        }
        return this;
    }

    public void checkRefundStatus() throws JsException {
        if (OrderStatusEnums.ORDER_STATUS_SHIPPED == this.getOrderStatus()) {
            throw new JsException(JsReturnCode.SYS000000.getMsg());
        }
        if (OrderStatusEnums.ORDER_STATUS_REFUNDAPPLY == this.getOrderStatus()
                || OrderStatusEnums.ORDER_STATUS_REFUNDING == this.getOrderStatus()
                || OrderStatusEnums.ORDER_STATUS_REFUNDFINISHED == this.getOrderStatus()) {
            throw new JsException(JsReturnCode.SYS000000.getMsg());
        }
    }
}
