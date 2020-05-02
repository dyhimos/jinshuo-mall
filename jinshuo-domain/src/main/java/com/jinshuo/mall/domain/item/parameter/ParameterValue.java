package com.jinshuo.mall.domain.item.parameter;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dongyh on 2019/11/18.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterValue extends IdentifiedEntity {
    private ParameterValueId parameterValueId;
    private Long parameterId;
    private String name; //参数名称
    private String param;//参数值
    private Integer type;// 0-> 可填充 1-> 标题  2-> 空白
    private Integer sort;// 排序
}
