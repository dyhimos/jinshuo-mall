package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/10/10.
 */
@Data
public class PriceCalendarMainDto {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @ApiModelProperty(value = "价格日历")
    private List<PriceCalendarDto> list;
}
