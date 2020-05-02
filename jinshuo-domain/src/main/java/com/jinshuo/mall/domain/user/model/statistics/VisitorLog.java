package com.jinshuo.mall.domain.user.model.statistics;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Administrator on 2020/4/2.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorLog extends IdentifiedEntity {
    private VisitorLogId visitorLogId;
    private Long userId;
    private String ipAddr;
    private Date visitFirstTime;
    private Date visitLastTime;
    private Integer visitCount;

    public void init() {
        this.visitorLogId = new VisitorLogId(CommonSelfIdGenerator.generateId());
        this.visitCount = 1;
        super.preInsert();
    }

    public void addCount() {
        if (null == this.visitCount) {
            this.visitCount = 1;
        }
        this.visitCount++;
        this.visitLastTime = new Date();
    }

}
