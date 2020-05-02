package com.jinshuo.mall.domain.item.marketing;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by dongyh on 2019/10/16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Marketing extends IdentifiedEntity {
    private MarketingId marketingId;
    private Long shopId;
    private Integer marketingType;//活动类型 1->秒杀
    private String name;
    private Date startTime;
    private Date endTime;
    private Integer goodsMode; //商品范围 0所有 1部分
    private Integer quantity;//限购数量 0为不限购
    private Integer sort;
    private String desc;
    private Integer marketingStatus;//活动状态 0正常 1停用

    public Marketing insert() {
        super.preInsert();
        this.marketingId = new MarketingId(CommonSelfIdGenerator.generateId());
        return this;
    }

    public Marketing update() {
       this.updateDate = new Date();
        return this;
    }
}
