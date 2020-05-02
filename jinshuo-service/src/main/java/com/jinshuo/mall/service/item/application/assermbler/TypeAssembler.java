package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.type.Type;
import com.jinshuo.mall.service.item.application.dto.TypeDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class TypeAssembler {
    /**
     * @param type
     * @return
     */
    public static TypeDto assembleTypeDto(Type type) {
        if (type == null) {
            return null;
        }
        TypeDto dto = new TypeDto();
        BeanUtils.copyProperties(type, dto);
        dto.setId(type.getTypeId().getId().toString());
        return dto;
    }
}
