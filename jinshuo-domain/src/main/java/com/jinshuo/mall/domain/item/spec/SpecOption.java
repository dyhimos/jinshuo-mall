package com.jinshuo.mall.domain.item.spec;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by dongyh on 2019/7/17.
 */
@Data
@Builder
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SpecOption extends IdentifiedEntity {
    private SpecOptionId specOptionId;
    private String name;
    private SpecId specId;
    private Integer sort;

    public SpecOption update(String name, SpecId specId, Integer sort) {
        this.name = name;
        this.specId = specId;
        this.sort = sort;
        return this;
    }
}
