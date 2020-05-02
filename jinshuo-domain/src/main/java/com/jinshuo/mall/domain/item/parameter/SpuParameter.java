package com.jinshuo.mall.domain.item.parameter;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dongyh on 2019/12/20.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpuParameter extends IdentifiedEntity {
    private SpuParameterId spuParameterId;
    private Long spuId;
    private Long parameterId;
    private Long parameterValueId;
    private Integer sort;
}
