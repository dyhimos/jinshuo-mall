package com.jinshuo.mall.service.order.application.qry;

import lombok.Builder;
import lombok.Data;

/**
 * @Classname SpuQry
 * @Description TODO
 * @Date 2019/6/27 15:43
 * @Created by dongyh
 */
@Data
@Builder
public class CodeQry {
    private Long skuId;

    private Long spuId;
}
