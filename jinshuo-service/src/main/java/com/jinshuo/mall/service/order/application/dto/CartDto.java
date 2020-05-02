package com.jinshuo.mall.service.order.application.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 19458 on 2019/9/18.
 */
@Data

public class CartDto {
    private String id;
    private Long userId;
    private Long shopId;
    private Integer quantity;
    private BigDecimal totalMoney;
    private List<CartItemDto> items;
    private PageInfo pageInfo;
}
