package com.jinshuo.mall.service.order.service.query;

import com.jinshuo.mall.domain.order.cart.CartItem;
import com.jinshuo.mall.service.item.application.dto.SpuDto;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.order.application.assermbler.CartAssembler;
import com.jinshuo.mall.service.order.application.dto.CartItemDto;
import com.jinshuo.mall.service.order.application.qry.CartItemQry;
import com.jinshuo.mall.service.order.mybatis.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 19458 on 2019/11/28.
 */
@Service
public class CartItemQueryService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private SpuQueryService spuQueryService;


    public List<CartItemDto> getMyCartItem(CartItemQry qry) {
        List<String> cartItemIds = Arrays.asList(qry.getCartItemIds().split(","));
        List<CartItemDto> dtos = new ArrayList<>();
        CartItemDto cartItemDto;
        for (String cartItemId : cartItemIds) {
            CartItem cartItem = cartItemRepo.queryById(Long.parseLong(cartItemId));
            cartItemDto = new CartItemDto();
            cartItemDto = CartAssembler.assembleCartItmeDto(cartItem);
            try {
                SpuQry spuQry = SpuQry.builder()
                        .skuId(Long.parseLong(cartItemDto.getSkuId()))
                        .build();
                SpuDto spuDto = spuQueryService.findByExemple(spuQry);
                if (null == spuDto) {
                    continue;
                }
                cartItemDto.setSkuName(spuDto.getSkus().get(0).getName());
                cartItemDto.setSpuName(spuDto.getName());
                cartItemDto.setPictureUrl(spuDto.getPictureUrl());
                cartItemDto.setGoodsPrice(spuDto.getSkus().get(0).getPrice());
                cartItemDto.setStock(spuDto.getSkus().get(0).getStock());
                cartItemDto.setSpuId(spuDto.getId());
            } catch (Exception e) {
            }
            dtos.add(cartItemDto);
        }
        return dtos;
    }
}
