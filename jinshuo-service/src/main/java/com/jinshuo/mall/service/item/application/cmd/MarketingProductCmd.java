package com.jinshuo.mall.service.item.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/10/16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketingProductCmd  {
    private Long marketingId;
    private Long spuId;
    private Integer stock;
    private BigDecimal price;
    private Integer sort;

}
