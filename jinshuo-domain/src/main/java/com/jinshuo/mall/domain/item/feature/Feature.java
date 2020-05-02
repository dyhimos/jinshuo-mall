package com.jinshuo.mall.domain.item.feature;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by dongyh on 2019/9/25.
 * 产品属性（特征）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feature extends IdentifiedEntity {
    private FeatureId featureId;
    private String name;
    private Integer goodsCount;
    private Integer sort;
    private Long shopId;
    private Long merchantId;

    public Feature insert() {
        this.preInsert();
        this.featureId = new FeatureId(CommonSelfIdGenerator.generateId());
        return this;
    }

    public Feature update(Long id) {
        this.featureId = new FeatureId(id);
        this.updateDate = new Date();
        return this;
    }
}
