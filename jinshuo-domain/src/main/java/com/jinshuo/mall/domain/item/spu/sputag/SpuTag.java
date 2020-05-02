package com.jinshuo.mall.domain.item.spu.sputag;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.domain.item.tag.TagId;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by dongyh on 2019/7/24.
 */
@Data
@Builder
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SpuTag extends IdentifiedEntity {
    private SpuTagId spuTagId;
    private TagId tagId;
    private SpuId spuId;
}
