package com.jinshuo.mall.front.order.user;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.order.cart.Cart;
import com.jinshuo.mall.service.order.application.cmd.CartItemCmd;
import com.jinshuo.mall.service.order.application.cmd.CartItemsCmd;
import com.jinshuo.mall.service.order.application.qry.CartItemQry;
import com.jinshuo.mall.service.order.application.qry.PageQry;
import com.jinshuo.mall.service.order.service.command.CartComService;
import com.jinshuo.mall.service.order.service.query.CartItemQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/9/18.
 */
@Component
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/wx/cart")
@Api(description = "购物车")
@AllArgsConstructor
public class WxCartRestApi {

    @Autowired
    private CartComService cartComService;

    @Autowired
    private CartItemQueryService cartItemQueryService;


    @PostMapping(value = "/addItem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "购物车添加商品")
    public WrapperResponse addItem(@RequestBody CartItemCmd cmd) {
        log.info(" -- 购物车添加商品：" + JSONObject.toJSONString(cmd));
        try {
            cartComService.addCartItem(cmd);
        } catch (OcBizException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
        return WrapperResponse.success();
    }


    @PostMapping(value = "/addItems", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "购物车添加商品(一次添加多个产品)")
    public WrapperResponse addItems(@RequestBody CartItemsCmd cmds) {
        log.info(" -- 购物车添加商品：" + JSONObject.toJSONString(cmds));
        try {
            for (CartItemCmd cmd : cmds.getItems()) {
                cartComService.addCartItem(cmd);
            }
        } catch (OcBizException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
        return WrapperResponse.success();
    }

    @PostMapping(value = "/deleteItem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "购物车删除商品")
    public WrapperResponse deleteItem(@RequestBody CartItemCmd cmd) {
        log.info(" -- 购物车删除商品：" + JSONObject.toJSONString(cmd));
        return WrapperResponse.success(cartComService.deleteCartItem(cmd));
    }

    @PostMapping(value = "/updateCartItem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "修改购物车商品")
    public WrapperResponse updateCartItem(@RequestBody CartItemCmd cmd) throws Exception {
        log.info(" -- 修改购物车删除商品：" + JSONObject.toJSONString(cmd));
        Cart cart = null;
        try {
            cart = cartComService.updateCartItem(cmd);
            cartComService.updateComputeAmount(cart);
        } catch (OcBizException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
        return WrapperResponse.success();
    }

    @PostMapping(value = "/getMyCart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "查询我的购物车")
    public WrapperResponse getMyCart(@RequestBody PageQry qry) {
        return WrapperResponse.success(cartComService.getMyCart(qry));
    }

    @PostMapping(value = "/getMyCartItem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "下单产品查询")
    public WrapperResponse getMyCartItem(@RequestBody CartItemQry qry) {
        return WrapperResponse.success(cartItemQueryService.getMyCartItem(qry));
    }

    @PostMapping(value = "/orderItem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "下单删除原购物车产品")
    public WrapperResponse orderItem(@RequestBody CartItemQry qry) {
        cartComService.orderItem(qry);
        return WrapperResponse.success();
    }

    @GetMapping(value = "/getCartCount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "GET", value = "获取购物车数量")
    public WrapperResponse getCartCount() {
        return WrapperResponse.success(cartComService.computeCount());
    }

}
