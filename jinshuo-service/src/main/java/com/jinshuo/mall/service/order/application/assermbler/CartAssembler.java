package com.jinshuo.mall.service.order.application.assermbler;

import com.jinshuo.mall.domain.order.cart.Cart;
import com.jinshuo.mall.domain.order.cart.CartItem;
import com.jinshuo.mall.service.order.application.dto.CartDto;
import com.jinshuo.mall.service.order.application.dto.CartItemDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname GoodsOrderAssembler
 * @Description GoodsOrderAssembler模块的组装器，完成domain model对象到dto的转换，组装职责包括：
 * 1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 * 2、将多个model组合成一个dto，一并返回。
 * @Date 2019/9/17 18:27
 * @Created by dyh
 */
@Component
public class CartAssembler {

    /**
     * 返回我的购物车
     *
     * @param cart
     * @return
     */
    public static CartDto assembleCartDto(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartDto dto = new CartDto();
        BeanUtils.copyProperties(cart, dto);
        dto.setId(cart.getCartId().getId().toString());
        return dto;
    }


    /**
     * 返回我的购物车
     *
     * @param cartItem
     * @return
     */
    public static CartItemDto assembleCartItmeDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        CartItemDto dto = new CartItemDto();
        BeanUtils.copyProperties(cartItem, dto);
        dto.setId(cartItem.getCartItemId().getId().toString());
        dto.setCartId(cartItem.getCartId().getId().toString());
        dto.setSkuId(cartItem.getSkuId().toString());
        return dto;
    }
}
