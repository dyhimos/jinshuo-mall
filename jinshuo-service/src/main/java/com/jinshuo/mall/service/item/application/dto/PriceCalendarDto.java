package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/10/10.
 */
@Data
public class PriceCalendarDto {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "价格日期")
    private Date date;

    @ApiModelProperty(value = "库存")
    private Integer quantity;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;
}
