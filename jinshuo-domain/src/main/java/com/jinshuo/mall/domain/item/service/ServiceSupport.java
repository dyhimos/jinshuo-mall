package com.jinshuo.mall.domain.item.service;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dongyh on 2019/9/30.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceSupport extends IdentifiedEntity {
    private ServiceSupportId serviceSupportId;
    private Long shopId;
    private String name;
    private String desc;
    private Integer sort;
    private Integer ssStatus;

    public ServiceSupport insert(){
        this.serviceSupportId = new ServiceSupportId(CommonSelfIdGenerator.generateId());
        super.preInsert();
        return this;
    }
}
