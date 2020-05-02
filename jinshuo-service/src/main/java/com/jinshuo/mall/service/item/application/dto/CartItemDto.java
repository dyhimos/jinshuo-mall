package com.jinshuo.mall.service.item.application.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

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
public class CartItemDto {
    private String id;
    private String cartId;
    private String spuId;
    private String skuId;
    private String skuName;
    private String spuName;
    private String pictureUrl;
    private BigDecimal goodsPrice;
    private BigDecimal addPrice;
    private Integer quantity;
    private Date addTime;
    private Integer stock;
}
