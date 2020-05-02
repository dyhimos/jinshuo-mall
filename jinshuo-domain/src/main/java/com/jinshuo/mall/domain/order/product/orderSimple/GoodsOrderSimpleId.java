package com.jinshuo.mall.domain.order.product.orderSimple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname GoodsOrderSimpleId
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dongyh
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrderSimpleId {
    private Long id;
}
