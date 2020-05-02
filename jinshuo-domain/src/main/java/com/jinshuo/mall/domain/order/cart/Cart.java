package com.jinshuo.mall.domain.order.cart;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/9/18.
 */
@Data
@Builder
@Getter
@Setter(AccessLevel.PUBLIC)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends IdentifiedEntity {
    private CartId cartId;
    private Long userId;
    private Long shopId;
    private Integer quantity;
    private BigDecimal totalMoney;

    /**
     * 初始化购物车
     *
     * @param userId
     * @param shopId
     * @return
     */
    public Cart init(Long userId, Long shopId) {
        this.userId = userId;
        this.shopId = shopId;
        this.quantity = 0;
        this.totalMoney = new BigDecimal(0);
        return this;
    }

    /**
     * 购物车原有基础上加商品
     *
     * @param quantity
     * @param totalMoney
     * @return
     */
    public Cart add(Integer quantity, BigDecimal totalMoney) {
        this.quantity = this.quantity + quantity;
        this.totalMoney = this.totalMoney.add(totalMoney);
        return this;
    }

    /**
     * 购物车减商品
     *
     * @param quantity
     * @param totalMoney
     * @return
     */
    public Cart minus(Integer quantity, BigDecimal totalMoney) {
        this.quantity = this.quantity - quantity;
        this.totalMoney = this.totalMoney.subtract(totalMoney);
        return this;
    }

    /**
     * 购物车先减去原有商品金额
     *
     * @param quantity
     * @param totalMoney
     * @return
     */
    public Cart reduceOriginal(Integer quantity, BigDecimal totalMoney) {
        this.quantity = this.quantity - quantity;
        this.totalMoney = this.totalMoney.subtract(totalMoney);
        return this;
    }
}
