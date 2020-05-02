package com.jinshuo.mall.domain.item.sku;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.item.spec.SpecId;
import com.jinshuo.mall.domain.item.spec.SpecOptionId;
import lombok.*;

/**
 * Created by dongyh on 2019/7/18.
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SkuOption extends IdentifiedEntity {
    private SkuOptionId skuOptionId;
    private SkuId skuId; //
    private SpecId specId; //规格id
    private SpecOptionId specOptionId; //规格属性id
    private Integer sort;

    public SkuOption insert() {
        super.preInsert();
        return this;
    }
}
