package com.jinshuo.mall.domain.order.product.orderAddress;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.util.Date;

/**
 * @author dongyh
 * @Classname GoodsOrderAddress
 * @Description 订单配送地址信息
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@ToString(callSuper = true)
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@Builder
public class GoodsOrderAddress {
    /**
     * 编号
     */
    private GoodsOrderAddressId goodsOrderAddressId;

    /**
     * 订单编号
     */
    private GoodsOrderId goodsOrderId;

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

    @Tolerate
    public GoodsOrderAddress() {

    }

    /**
     * 保存收货地址
     *
     * @param goodsOrderAddressId 地址ID
     * @param goodsOrderId        订单编号
     * @param userName            收货人姓名
     * @param userAddress         收货人地址
     * @param userPhone           收货人电话
     * @return
     */
    public GoodsOrderAddress save(GoodsOrderAddressId goodsOrderAddressId, GoodsOrderId goodsOrderId, String userName, String userAddress, String userPhone) {
        this.goodsOrderAddressId = goodsOrderAddressId;
        this.goodsOrderId = goodsOrderId;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.createDate = new Date();
        this.updateDate = new Date();
        this.remarks = remarks;
        this.status = Status.NORMAL;
        this.version = 1;
        return this;
    }
}
