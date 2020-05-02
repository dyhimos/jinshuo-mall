package com.jinshuo.mall.service.order.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.order.cart.CartItem;
import com.jinshuo.mall.domain.order.cart.CartItemId;
import com.jinshuo.mall.service.order.mybatis.mapper.CartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车商品详情
 *
 * @Classname CartRepo
 * @Description TODO
 * @Date 2019/9/16 20:07
 * @Created by dyh
 */
@Repository
public class CartItemRepo {


    @Autowired(required = false)
    private CartItemMapper cartItemMapper;

    /**
     * 根据购物查id查询我的购物车清单
     *
     * @return
     */
    public List<CartItem> queryMyCart(Long userId) {
        return cartItemMapper.queryBycartId(userId);
    }


    /**
     * 添加商品
     *
     * @param cartItem
     */
    public void save(CartItem cartItem) {
        if (null==cartItem.getCartItemId()) {
            cartItem.setCartItemId(nextId());
            cartItemMapper.create(cartItem);
        }else{
            cartItemMapper.update(cartItem);
        }
    }


    /**
     * 修改商品
     *
     * @param cartItem
     */
    public void update(CartItem cartItem) {
        cartItemMapper.update(cartItem);
    }


    /**
     * 获取id
     *
     * @return
     */
    public CartItemId nextId() {
        return new CartItemId(CommonSelfIdGenerator.generateId());
    }


    /**
     * 删除商品
     *
     * @param id
     */
    public void delete(Long id) {
        cartItemMapper.delete(id);
    }

    /**
     * 根据商品id 查询记录
     *
     * @return
     */
    public CartItem queryBySkuId(Long cartId, Long skuId) {
        return cartItemMapper.queryBySkuId(cartId, skuId);
    }

    /**
     * 修改商品记录
     *
     * @return
     */
    public int minus(CartItem cartItem) {
        return cartItemMapper.minus(cartItem);
    }

    /**
     * 根据商品id 查询记录
     *
     * @return
     */
    public CartItem queryById(Long id) {
        return cartItemMapper.queryById(id);
    }

    /**
     * 根据购物车id 查询记录
     *
     * @return
     */
    public List<CartItem> queryCartId(Long id) {
        return cartItemMapper.queryCartId(id);
    }

}
