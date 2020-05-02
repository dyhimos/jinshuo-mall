package com.jinshuo.mall.service.order.service.command;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.exception.order.OcReturnCode;
import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.order.cart.Cart;
import com.jinshuo.mall.domain.order.cart.CartItem;
import com.jinshuo.mall.service.item.application.dto.SpuDto;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.order.application.assermbler.CartAssembler;
import com.jinshuo.mall.service.order.application.cmd.CartItemCmd;
import com.jinshuo.mall.service.order.application.dto.CartDto;
import com.jinshuo.mall.service.order.application.dto.CartItemDto;
import com.jinshuo.mall.service.order.application.qry.CartItemQry;
import com.jinshuo.mall.service.order.application.qry.PageQry;
import com.jinshuo.mall.service.order.mybatis.CartItemRepo;
import com.jinshuo.mall.service.order.mybatis.CartRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/9/18.
 */
@Slf4j
@Service
public class CartComService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private SpuQueryService spuQueryService;



    /**
     * 添加购物车商品
     *
     * @param cmd
     * @return
     */
    @Transactional
    public int addCartItem(CartItemCmd cmd) throws Exception {
        log.info(" -- 添加购物车商品,输入参数：{}", cmd);
        Long userId = UserIdUtils.getUserId();
        Long shopId = UserIdUtils.getUser().getShopId();
        SpuQry spuQry = SpuQry.builder()
                .skuId(cmd.getSkuId())
                .build();
        //SpuDto spuDto = itemServiceResponse.getByExemple(spuQry);
        SpuDto spuDto = spuQueryService.findByExemple(spuQry);
        if (null == spuDto) {
            throw new OcBizException(OcReturnCode.OC202002.getMsg());
        }
        cmd.setGoodsPrice(spuDto.getSkus().get(0).getPrice());
        Cart temp = cartRepo.queryMyCart(userId);
        CartItem cartItem = null;
        if (temp != null) {
            cartItem = cartItemRepo.queryBySkuId(temp.getCartId().getId(), cmd.getSkuId());
        }
        Cart cart = addCart(cmd, cartItem, userId, shopId);
        if (null == cartItem) {
            cartItem = CartItem.builder()
                    .cartId(cart.getCartId())
                    .addPrice(cmd.getGoodsPrice())
                    .goodsPrice(cmd.getGoodsPrice())
                    .skuId(cmd.getSkuId())
                    .quantity(cmd.getQuantity())
                    .addTime(new Date())
                    .build();
            cartItem.insert();
        } else {
            cartItem.addItem(cmd.getQuantity(), cmd.getGoodsPrice());
            cartItem.update();
        }
        cartItemRepo.save(cartItem);
        return 1;
    }

    /**
     * 删除购物车商品
     *
     * @param cmd
     * @return
     */
    public int deleteCartItem(CartItemCmd cmd) {
        log.info(" -- 删除购物车商品,输入参数 {}", cmd);
        Long userId = UserIdUtils.getUserId();
        Cart cart = cartRepo.queryMyCart(userId);
        CartItem cartItem = cartItemRepo.queryBySkuId(cart.getCartId().getId(), cmd.getSkuId());
        cartItem.minus(cmd.getQuantity());
        if (Status.DELETE.code == cartItem.getStatus().getValue()) {
            cartItemRepo.delete(cartItem.getCartItemId().getId());
        } else {
            cartItemRepo.minus(cartItem);
        }
        BigDecimal quantityTemp = new BigDecimal(cmd.getQuantity());
        BigDecimal totalAmount = quantityTemp.multiply(cartItem.getAddPrice());
        cart.minus(cmd.getQuantity(), totalAmount);
        cartRepo.update(cart);
        return 1;
    }


    /**
     * 修改购物车商品
     *
     * @param cmd
     * @return
     */
    @Transactional
    public Cart updateCartItem(CartItemCmd cmd) throws Exception {
        log.info(" -- 修改购物车商品,输入参数：{}", cmd);
        Long userId = UserIdUtils.getUserId();
        Long shopId = UserIdUtils.getUser().getShopId();
        SpuQry spuQry = SpuQry.builder()
                .skuId(cmd.getSkuId())
                .build();
        //SpuDto spuDto = itemServiceResponse.getByExemple(spuQry);
        SpuDto spuDto = spuQueryService.findByExemple(spuQry);
        if (null == spuDto) {
            throw new OcBizException(OcReturnCode.OC202002.getMsg());
        }
        cmd.setGoodsPrice(spuDto.getSkus().get(0).getPrice());
        Cart temp = cartRepo.queryMyCart(userId);
        CartItem cartItem = null;
        if (temp != null) {
            cartItem = cartItemRepo.queryBySkuId(temp.getCartId().getId(), cmd.getSkuId());
        }
        Cart cart = addCart(cmd, cartItem, userId, shopId);
        if (null == cartItem) {
            cartItem = CartItem.builder()
                    .cartId(cart.getCartId())
                    .addPrice(cmd.getGoodsPrice())
                    .goodsPrice(cmd.getGoodsPrice())
                    .skuId(cmd.getSkuId())
                    .quantity(cmd.getQuantity())
                    .addTime(new Date())
                    .build();
            cartItem.insert();
        } else {
            cartItem.updateItem(cmd.getQuantity(), cmd.getGoodsPrice());
            cartItem.update();
        }
        cartItemRepo.save(cartItem);
        return cart;
    }


    /**
     * 添加购物车
     *
     * @param cmd
     * @return
     */
    @Transactional
    public Cart addCart(CartItemCmd cmd, CartItem cartItem, Long userId, Long shopId) {
        Cart cart = cartRepo.queryMyCart(userId);
        if (null == cart) {
            cart = new Cart();
            cart.init(userId, shopId);
        }
        Integer resultQuantity = cmd.getQuantity();
        BigDecimal quantity = new BigDecimal(cmd.getQuantity());
        BigDecimal totalMoney = quantity.multiply(cmd.getGoodsPrice());
        if (null != cartItem) {
            //原有数量
            BigDecimal originalQuantity = new BigDecimal(cartItem.getQuantity());
            //原有金额
            BigDecimal originalAmount = originalQuantity.multiply(cartItem.getAddPrice());
            cart.reduceOriginal(cartItem.getQuantity(), originalAmount);
            resultQuantity = cmd.getQuantity() + cartItem.getQuantity();
            quantity = new BigDecimal(resultQuantity);
            totalMoney = quantity.multiply(cmd.getGoodsPrice());
        }
        cart.add(resultQuantity, totalMoney);
        cartRepo.update(cart);
        return cart;
    }


    /**
     * 查询我的购物车
     *
     * @return
     */
    public CartDto getMyCart(PageQry qry) {
        Long userId = UserIdUtils.getUserId();
        Cart cart = cartRepo.queryMyCart(userId);
        if (null == cart) {
            cart = new Cart();
            return null;
        }
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<CartItem> list = cartItemRepo.queryMyCart(cart.getCartId().getId());
        BigDecimal amount = new BigDecimal(0);
        for (CartItem cartItem : list) {
            if (null != cartItem.getAddPrice() && null != cartItem.getQuantity()) {
                amount = amount.add(cartItem.getAddPrice().subtract(new BigDecimal(cartItem.getQuantity())));
            }
        }
        cart.setTotalMoney(amount);
        PageInfo pageInfo = new PageInfo<>(list);
        List<CartItemDto> items = new ArrayList<>();
        CartItemDto cartItemDto;
        for (CartItem cartItem : list) {
            cartItemDto = new CartItemDto();
            cartItemDto = CartAssembler.assembleCartItmeDto(cartItem);
            try {
                SpuQry spuQry = SpuQry.builder()
                        .skuId(Long.parseLong(cartItemDto.getSkuId()))
                        .build();
                //SpuDto spuDto = itemServiceResponse.getByExemple(spuQry);
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
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            items.add(cartItemDto);
        }
        CartDto dto = CartAssembler.assembleCartDto(cart);
        //dto.setItems(items);
        pageInfo.setList(items);
        dto.setPageInfo(pageInfo);
        return dto;
    }


    /**
     * 下单后删除购物车
     *
     * @param qry
     */
    public void orderItem(CartItemQry qry) {
        List<String> cartItemIds = Arrays.asList(qry.getCartItemIds().split(","));
        for (String carItemId : cartItemIds) {
            cartItemRepo.delete(Long.parseLong(carItemId));
        }
    }

    /**
     * 修改购物车合计金额
     *
     * @param cart
     */
    public Cart updateComputeAmount(Cart cart) {
        computeAmount(cart);
        cartRepo.update(cart);
        return cart;
    }

    /**
     * 统计购物车合计金额
     *
     * @param cart
     */
    public Cart computeAmount(Cart cart) {
        List<CartItem> items = cartItemRepo.queryCartId(cart.getCartId().getId());
        BigDecimal amount = items.stream().map(CartItem::getAddPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalMoney(amount);
        return cart;
    }

    /**
     * 统计购物车数量
     *
     * @param
     */
    public Integer computeCount() {
        Cart cart = cartRepo.queryMyCart(UserIdUtils.getUserId());
        if (null == cart) {
            return 0;
        }
        List<CartItem> items = cartItemRepo.queryCartId(cart.getCartId().getId());
        if (null == items || items.size() < 1) {
            return 0;
        }
        int count = items.stream().mapToInt(CartItem::getQuantity).sum();
        return count;
    }



}
