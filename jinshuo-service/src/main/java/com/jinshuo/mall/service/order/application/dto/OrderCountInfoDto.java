package com.jinshuo.mall.service.order.application.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/12/13.
 */
@Data
public class OrderCountInfoDto {
    private Integer unpay;// 待付款订单数
    private Integer pay;// 待发货订单数
    private Integer activist;//维权
    private Integer activityCount;//营销活动数
    private Integer soldOutCount;//售罄产品数

    private BigDecimal amount;// 成交额
    private Integer itemCount;//产品数
    private Integer orderCount;//订单数

    private Integer accessCount;//访问次数
    private Integer accessMember;//访问会员数
    private Integer accessMan;//访问人数


    public void init() {
        this.unpay = 0;
        this.pay = 0;
        this.activist = 0;
        this.activityCount = 0;
        this.soldOutCount = 0;
        this.itemCount = 0;
        this.orderCount = 0;
        this.accessCount = 0;
        this.accessMember = 0;
        this.accessMan = 0;
        this.amount = new BigDecimal(0);
    }

    public void addAmount(BigDecimal amount) {
        if (null == amount) {
            return;
        }
        this.amount = this.amount.add(amount);
    }

    public void addItemCount(Integer itemCount) {
        this.itemCount = this.itemCount + itemCount;
    }
}
