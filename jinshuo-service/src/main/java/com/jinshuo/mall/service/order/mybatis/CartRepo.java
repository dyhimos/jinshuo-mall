package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.cart.Cart;
import com.jinshuo.mall.domain.order.cart.CartId;
import com.jinshuo.mall.service.order.mybatis.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 购物车
 *
 * @Classname CartRepo
 * @Description TODO
 * @Date 2019/9/16 20:07
 * @Created by dyh
 */
@Repository
public class CartRepo {


    @Autowired(required = false)
    private CartMapper mapper;

    /**
     * 获取我的购物车
     *
     * @return
     */
    public Cart queryMyCart(Long userId) {
        return mapper.queryByUserId(userId);
    }


    /**
     * 保存购物车
     *
     * @param cart
     */
    public void save(Cart cart) {
        mapper.create(cart);
    }


    /**
     * 修改购物车
     *
     * @param cart
     */
    public void update(Cart cart) {
        if (null == cart.getCartId()) {
            cart.setCartId(nextId());
            mapper.create(cart);
        } else {
            mapper.update(cart);
        }
    }


    /**
     * 获取id
     *
     * @return
     */
    public CartId nextId() {
        return new CartId(CommonSelfIdGenerator.generateId());
    }


}
