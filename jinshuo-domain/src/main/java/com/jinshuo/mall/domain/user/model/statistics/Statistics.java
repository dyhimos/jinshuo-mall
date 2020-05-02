package com.jinshuo.mall.domain.user.model.statistics;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2020/4/1.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics extends IdentifiedEntity {
    private StatisticsId statisticsId;
    /**
     * 访问类型 1 页面
     */
    private Integer statisticsType;
    /**
     * 目标代码 如页面代码
     */
    private String statisticsCode;

    private Integer count;

    public void init() {
        this.statisticsId = new StatisticsId(CommonSelfIdGenerator.generateId());
        this.count = 1;
        super.preInsert();
    }

    public void addCount() {
        this.count++;
    }
}
