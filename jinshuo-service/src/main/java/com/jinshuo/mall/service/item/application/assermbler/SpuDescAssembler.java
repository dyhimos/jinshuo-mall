package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.desc.SpuDesc;
import com.jinshuo.mall.service.item.application.dto.SpuDescDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/23.
 */
public class SpuDescAssembler {

    public static SpuDescDto assembleSpuDto(SpuDesc spuDesc) {
        if (spuDesc == null) {
            return null;
        }
        SpuDescDto SpuDescDto = new SpuDescDto();
        BeanUtils.copyProperties(spuDesc, SpuDescDto);
        SpuDescDto.setId(spuDesc.getSpuDescId().getId().toString());
        SpuDescDto.setSpuId(spuDesc.getSpuId().getId().toString());
        return SpuDescDto;
    }
}
