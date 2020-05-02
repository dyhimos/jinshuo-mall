package com.jinshuo.mall.domain.order.product.orderExpress;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.mall.domain.order.product.order.GoodsOrder;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dongyh
 * @Classname GoodsOrderDetail
 * @Description 快递
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrderExpress {

    /**
     * 快递Id
     */
    private GoodsOrderExpressId goodsOrderExpressId;

    /**
     * 订单
     */
    private GoodsOrder goodsOrder;

    /**
     * 订单id
     */
    private GoodsOrderId goodsOrderId;


    /**
     * 快递名称
     */
    private String expressCompanyName;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 快递编码
     */
    private String expressCode;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
