package com.jinshuo.mall.domain.item.type;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by dongyh on 2019/7/24.
 */
@Data
@ToString(callSuper = true)
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Type extends IdentifiedEntity {
    private TypeId typeId;
    private String name;
    private Integer goodsCount;
    private Integer sort;

    public Type update(String name, Integer sort) {
        this.name = name;
        this.goodsCount = goodsCount;
        this.sort = sort;
        return this;
    }
}
