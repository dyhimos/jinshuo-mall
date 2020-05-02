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
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement extends IdentifiedEntity {
    private AdvertisementId advertisementId;
    private AdPositionId adPositionId;
    private String titile;
    private String image;
    private String url ;
    private Integer sort;
    private Integer isEnabled;//是否可用 0->可用 1->不可用
    private String areaNames;//地区

    public Advertisement insert(){
        this.advertisementId = new AdvertisementId(CommonSelfIdGenerator.generateId());
        super.preInsert();
        return this;
    }

    public Advertisement update(Long id){
        this.advertisementId = new AdvertisementId(id);
        this.updateDate = new Date();
        return this;
    }
}
