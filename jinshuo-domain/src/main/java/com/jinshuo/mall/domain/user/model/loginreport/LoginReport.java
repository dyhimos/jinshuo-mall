package com.jinshuo.mall.domain.user.model.loginreport;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.Data;

/**
 * Created by 19458 on 2019/12/13.
 */
@Data
public class LoginReport extends IdentifiedEntity {
    private LoginReportId loginReportId;
    private Long shopId;
    private Long merchantId;
    private Integer memberCount;
    private Integer accessCount;

    public void init(Long shopId) {
        this.loginReportId = new LoginReportId(CommonSelfIdGenerator.generateId());
        super.preInsert();
        this.shopId = shopId;
        this.memberCount = 1;
        this.accessCount = 1;
    }

    public void addTimes() {
        this.accessCount = this.accessCount + 1;
    }
}
