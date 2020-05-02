package com.jinshuo.mall.domain.item.spushop;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dongyh on 2019/9/24.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpuShop extends IdentifiedEntity {
    private SpuShopId spuShopId;
    private Long spuId;
    private Long shopId;

    public SpuShop insert(){
        this.preInsert();
        this.setSpuShopId(new SpuShopId(CommonSelfIdGenerator.generateId()));
        return this;
    }
}
