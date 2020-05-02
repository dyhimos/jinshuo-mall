package com.jinshuo.mall.domain.item.ad;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by dongyh on 2019/9/10.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdPosition extends IdentifiedEntity {
    private AdPositionId adPositionId;
    private Integer isCycle; //是否轮循 0是 1不是
    private String code; //广告位代码
    private String name;
    private Long shopId;
    private String desc;
    private Integer adStatus; //广告位状态
    private Date startTime; //广告开始时间
    private Date endTime; //广告结束时间
    private Integer showType; //展示类型 0每次显示 1间隔显示
    private String gapTime; //间隔时间
    private String areaIds; //地区
    private Integer memberType; //会员类型

    public AdPosition insert() {
        this.adPositionId = new AdPositionId(CommonSelfIdGenerator.generateId());
        super.preInsert();
        return this;
    }

    public AdPosition update(Long id) {
        this.adPositionId = new AdPositionId(id);
        this.updateDate = new Date();
        return this;
    }
}
