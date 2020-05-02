package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.spu.sputag.SpuTag;
import com.jinshuo.mall.service.item.application.dto.SpuTagDto;

/**
 * Created by 19458 on 2019/7/24.
 */
public class SpuTagAssembler {
    /**
     * @param spuTag
     * @return
     */
    public static SpuTagDto assembleSpuTagDto(SpuTag spuTag) {
        if (spuTag == null) {
            return null;
        }
        SpuTagDto dto = new SpuTagDto();
        dto.setId(spuTag.getSpuTagId().getId().toString());
        dto.setSpuId(spuTag.getSpuId().getId().toString());
        dto.setTagId(spuTag.getTagId().getId().toString());
        return dto;
    }
}
