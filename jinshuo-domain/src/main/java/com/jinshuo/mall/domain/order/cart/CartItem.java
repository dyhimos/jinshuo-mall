package com.jinshuo.mall.domain.order.cart;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/9/18.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends IdentifiedEntity {
    private CartItemId cartItemId;
    private CartId cartId;
    private Long skuId;
    private BigDecimal goodsPrice;
    private BigDecimal addPrice;
    private Integer quantity;
    private Date addTime;

    //减商品
    public CartItem minus(Integer quantity) {
        this.quantity = this.quantity - quantity;
        if (this.quantity < 1) {
            this.status = Status.DELETE;
        }
        return this;
    }

    //原有基础上加商品
    public CartItem addItem(Integer quantity, BigDecimal addPrice) {
        this.quantity = this.quantity + quantity;
        this.addPrice = addPrice;
        this.goodsPrice = addPrice;
        return this;
    }

    //原有基础上加商品
    public CartItem updateItem(Integer quantity, BigDecimal addPrice) {
        this.quantity =  + quantity;
        this.addPrice = addPrice;
        this.goodsPrice = addPrice;
        return this;
    }

    public CartItem insert() {
        super.preInsert();
        return this;
    }

    public CartItem update() {
        this.updateDate = new Date();
        return this;
    }
}
