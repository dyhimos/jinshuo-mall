package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/10/16.
 */
@Data
public class MarketingProductDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long marketingId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long spuId;
    private Integer stock;
    private BigDecimal price;
    private Integer sort;

}
