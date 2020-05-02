package com.jinshuo.mall.domain.item.parameter;

import com.google.common.base.Joiner;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by dongyh on 2019/11/18.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parameter extends IdentifiedEntity {
    private ParameterId parameterId;
    private Long shopId;//店铺id
    private String name;// 参数名称
    private String type; // 0-> 商品列表页筛选  1-> 分类页筛选
    private Integer sort;
    private Integer singleFlag;//是否单选 0是单选 1否（多选）

    public Parameter update(String name, List<Integer> type, Integer sort, Integer singleFlag) {
        this.singleFlag = singleFlag;
        this.name = name;
        if (null != type && type.size() > 0) {
            this.type = Joiner.on(",").join(type);
        }
        this.sort = sort;
        this.updateDate = new Date();
        return this;
    }
}
