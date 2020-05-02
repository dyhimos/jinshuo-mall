package com.jinshuo.mall.domain.item.topic;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dongyh on 2019/9/29.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicProduct extends IdentifiedEntity {
    private TopicProductId topicProductId;
    private Long topicId;
    private Long spuId;
    private Integer sort;

    public TopicProduct build(Long topicId, Long spuId, Integer sort) {
        this.topicProductId = new TopicProductId(CommonSelfIdGenerator.generateId());
        this.topicId = topicId;
        this.spuId = spuId;
        this.sort = sort;
        super.preInsert();
        return this;
    }
}
