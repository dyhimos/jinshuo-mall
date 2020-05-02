package com.jinshuo.mall.domain.item.unit.model;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;


/**
 * Created by dyh
 * Time 2019/7/17 下午2:59
 */
@Data
@Builder
@Accessors(chain = true)
public class GoodsUnit extends IdentifiedEntity {

    private GoodsUnitId unitId;

    @Size(min = 1, max = 20, message = "单位名称字符数为[1-20]")
    private String name;


    protected GoodsUnit() {
    }

    public GoodsUnit(GoodsUnitId unitId, String name) {
        this.unitId = unitId;
        this.name = name;
        this.preInsert();
    }

    public GoodsUnit changName(String name) {
        this.name = name;
        this.update();
        return this;
    }

    public GoodsUnit changStatus(String name) {
        this.name = name;
        return this;
    }

    public GoodsUnit update() {
        this.version += 1;
        return this;
    }
}
