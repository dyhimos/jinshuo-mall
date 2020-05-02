package com.jinshuo.mall.domain.order.product.orderSimple;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.util.Date;

/**
 * @author dongyh
 * @Classname GoodsOrderAddress
 * @Description 寄样订单信息
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@Builder
public class GoodsOrderSimple extends IdentifiedEntity {

    /**
     * 店铺Id
     */
    private Long shopId;

    /**
     * 用户Id
     */
    private Long memberId;

    /**
     * 编号
     */
    private GoodsOrderSimpleId goodsOrderSimpleId;

    /**
     * 寄样单号
     */
    private String sampleNo;

    /**
     * 寄样信息
     */
    private String sampleInfo;

    /**
     * 收货人名称
     */
    private String userName;

    /**
     * 收件人地址
     */
    private String userAddress;

    /**
     * 收件人手机号码
     */
    private String userPhone;

    /**
     * 寄样订单状态 1：待发货  2：待收货
     */
    private Integer sampleStatus;

    /**
     * 快递公司
     */
    private String expressCompany;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 快递公司编码
     */
    private String expressCode;

    /**
     * 发货时间
     */
    private Date expressDate;


    @Tolerate
    public GoodsOrderSimple() {

    }

    /**
     * 保存收货地址
     *
     * @param goodsOrderSimpleId 寄样信息
     * @param sampleInfo         寄样信息
     * @param userName           收货人姓名
     * @param userAddress        收货人地址
     * @param userPhone          收货人电话
     * @return
     */
    public GoodsOrderSimple save(GoodsOrderSimpleId goodsOrderSimpleId, String sampleNo, Long memberId, Long shopId, String sampleInfo, String userName,
                                 String userAddress, String userPhone) {
        super.preInsert();
        this.sampleNo = sampleNo;
        this.memberId = memberId;
        this.goodsOrderSimpleId = goodsOrderSimpleId;
        this.shopId = shopId;
        this.sampleInfo = sampleInfo;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.sampleStatus = 1;
        return this;
    }
}
