package com.jinshuo.mall.domain.order.product.expressCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ExpressCode {

    /**
     * 快递Id
     */
    private ExpressCodeId expressCodeId;

    /**
     * 快递名称
     */
    private String expressCompanyName;

    /**
     * 快递编码
     */
    private String expressCode;
}
