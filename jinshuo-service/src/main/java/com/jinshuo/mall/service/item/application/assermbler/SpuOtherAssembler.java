package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.spuOther.SpuOther;
import com.jinshuo.mall.service.item.application.dto.SpuOtherDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class SpuOtherAssembler {

    /**
     * @param spuOther
     * @return
     */
    public static SpuOtherDto assembleSpuOtherDto(SpuOther spuOther) {
        if (spuOther == null) {
            return null;
        }
        SpuOtherDto dto = new SpuOtherDto();
        BeanUtils.copyProperties(spuOther, dto);
        dto.setId(spuOther.getSpuOtherId().getId().toString());
        dto.setSpuId(spuOther.getSpuId().getId().toString());
        return dto;
    }
}
