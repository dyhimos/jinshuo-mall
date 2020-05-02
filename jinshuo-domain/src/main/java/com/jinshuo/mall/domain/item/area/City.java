package com.jinshuo.mall.domain.item.area;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by dongyh on 2019/10/28.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City extends IdentifiedEntity {
    private CityId cityId;
    private Long shopId;
    private Long areaId;
    private String areaProName;
    private String areaName;
    private String areaCode;
    private Date openTime;
    private String openBusiness;

    public City insert(){
        this.preInsert();
        this.cityId = new CityId(CommonSelfIdGenerator.generateId());
        return this;
    }

    public City update(Long id){
        this.cityId = new CityId(id);
        this.updateDate = new Date();
        return this;
    }
}
