package com.jinshuo.mall.service.order.application.dto;

import lombok.Data;

/**
 * @Classname GoodsOrderDto
 * @Description 返回的订单列表
 * @Date 2019/6/28 9:29
 * @Created by dongyh
 * @author  dongyh
 */
@Data
public class GoodsOrderStatusDto {

    private Integer code;
    private String desc;
    private Integer value;
    private String displayName;
}
