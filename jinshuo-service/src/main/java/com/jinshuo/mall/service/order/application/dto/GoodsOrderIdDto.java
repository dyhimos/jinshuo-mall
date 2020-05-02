package com.jinshuo.mall.service.order.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Classname GoodsOrderIdDto
 * @Description 返回的订单ID
 * @Date 2019/6/28 9:29
 * @Created by dongyh
 * @author  dongyh
 */
@Data
public class GoodsOrderIdDto {

    /**
     * 订单id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String orderNo;
}
