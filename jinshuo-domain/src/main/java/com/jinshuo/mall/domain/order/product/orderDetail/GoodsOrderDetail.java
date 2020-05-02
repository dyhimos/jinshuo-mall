package com.jinshuo.mall.domain.order.product.orderDetail;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCode;
import lombok.*;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author dongyh
 * @Classname GoodsOrderDetail
 * @Description 订单详情
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@AllArgsConstructor
public class GoodsOrderDetail {

    /**
     * 订单详情id
     */
    private GoodsOrderDetailId goodsOrderDetailId;

    /**
     * 供应商id
     */
    private Long supplierId;


    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 运费
     */
    private BigDecimal logisticsFee;

    /**
     * 订单编码
     */
    private GoodsOrderId goodsOrderId;

    /**
     * 商品编码
     */
    private Long goodsSpuId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * skuName
     */
    private String skuName;

    /**
     * 商品图片
     */
    private String goodsImage;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 商品SKU
     */
    private Long goodsSkuId;
    /**
     * 折扣比例
     */
    private Integer discountRate;
    /**
     * 折扣比例金额
     */
    private BigDecimal discountAmount;
    /**
     * 购买数量
     */
    private Integer number;
    /**
     * 小计金额
     */
    private BigDecimal subtotal;
    /**
     * 商品是否有效
     */
    private Integer isGoodsExists;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private Status status;

    /**
     * 版本
     */
    private Integer version;


    /**
     * 是否发码产品 0 是 1：不是
     */
    private Integer isSendcode;

    /**
     * 预约地址
     */
    private String reserveAddress;

    /**
     * 自动发码（0：是 1：否）
     */
    private Integer autoSendCode;

    /**
     * 虚拟产品核销码信息
     */
    private List<OrderVerificationCode> verificationCodeList;

    @Tolerate
    public GoodsOrderDetail() {
    }

    /**
     * 保存订单
     *
     * @param goodsOrderDetailId
     * @param goodsOrderId
     * @param goodsSpuId
     * @param goodsName
     * @param goodsPrice
     * @param goodsSkuId
     * @param discountRate
     * @param discountAmount
     * @param number
     * @param remarks
     * @return
     */
    public GoodsOrderDetail save(GoodsOrderDetailId goodsOrderDetailId, Long supplierId, String supplierName, BigDecimal costPrice,
                                 BigDecimal logisticsFee, GoodsOrderId goodsOrderId, Long goodsSpuId, String goodsName, String skuName,
                                 String goodsImage, BigDecimal goodsPrice, Long goodsSkuId, Integer discountRate,
                                 BigDecimal discountAmount, Integer number, String remarks,
                                 Integer isSendcode, String reserveAddress, Integer autoSendCode
    ) {
        this.goodsOrderDetailId = goodsOrderDetailId;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        //运费
        this.logisticsFee = logisticsFee;
        //成本价
        this.costPrice = costPrice;
        this.goodsOrderId = goodsOrderId;
        this.goodsSpuId = goodsSpuId;
        this.goodsName = goodsName;
        this.skuName = skuName;
        this.goodsImage = goodsImage;
        this.goodsPrice = goodsPrice;
        this.goodsSkuId = goodsSkuId;
        this.discountRate = discountRate;
        this.discountAmount = discountAmount;
        this.number = number;
        this.subtotal = goodsPrice.multiply(new BigDecimal(number));
        this.isGoodsExists = 0;
        this.createDate = new Date();
        this.updateDate = new Date();
        this.remarks = remarks;
        this.status = Status.NORMAL;
        this.version = 1;
        this.isSendcode = isSendcode;
        this.reserveAddress = reserveAddress;
        this.autoSendCode = autoSendCode;
        return this;
    }
}
