package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.unit.model.GoodsUnit;
import com.jinshuo.mall.service.item.application.dto.GoodUnitDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by 19458 on 2019/7/12.
 */
@Component
public class UnitAssembler {

    /**
     * @param goodsUnit
     * @return
     */
    public static GoodUnitDto assembleUnitDto(GoodsUnit goodsUnit) {
        if (goodsUnit == null) {
            return null;
        }
        GoodUnitDto goodUnitDto = new GoodUnitDto();
        BeanUtils.copyProperties(goodsUnit, goodUnitDto);
        goodUnitDto.setId(goodsUnit.getUnitId().getId().toString());
        return goodUnitDto;
    }
}
