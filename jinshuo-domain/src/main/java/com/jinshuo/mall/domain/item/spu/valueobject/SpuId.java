package com.jinshuo.mall.domain.item.spu.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname SpuId
 * @Description TODO
 * @Date 2019/6/16 19:43
 * @Created by dyh
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SpuId {
    private Long id;
}
