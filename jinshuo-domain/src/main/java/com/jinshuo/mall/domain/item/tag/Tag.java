package com.jinshuo.mall.domain.item.tag;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dongyh on 2019/7/19.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends IdentifiedEntity {
    private TagId tagId;
    private String name;
    private Integer goodsCount;
    private Integer sort;
    private Long shopId;

    public Tag update(String name, Integer sort, Long shopId) {
        this.name = name;
        this.goodsCount = goodsCount;
        this.sort = sort;
        if (null != shopId) {
            this.shopId = shopId;
        }
        return this;
    }
}
