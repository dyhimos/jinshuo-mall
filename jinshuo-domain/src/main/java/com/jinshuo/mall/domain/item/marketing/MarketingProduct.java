package com.jinshuo.mall.domain.item.marketing;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by dongyh on 2019/10/16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketingProduct extends IdentifiedEntity {
    private MarketingProductId marketingProductId;
    private Long marketingId;
    private Long spuId;
    private Integer stock;
    private BigDecimal price;
    private Integer sort;

    public MarketingProduct insert() {
        super.preInsert();
        return this;
    }
}
