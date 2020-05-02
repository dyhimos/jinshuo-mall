package com.jinshuo.mall.service.order.application.cmd;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/9/18.
 */
@Data
public class CartItemsCmd {
    private List<CartItemCmd> items;
}
