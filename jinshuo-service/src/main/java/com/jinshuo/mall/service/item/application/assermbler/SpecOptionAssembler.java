package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.spec.SpecOption;
import com.jinshuo.mall.service.item.application.dto.SpecOptionDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/17.
 */
public class SpecOptionAssembler {

    /**
     * @param specOption
     * @return
     */
    public static SpecOptionDto assembleSpecOptionDto(SpecOption specOption) {
        if (specOption == null) {
            return null;
        }
        SpecOptionDto dto = new SpecOptionDto();
        BeanUtils.copyProperties(specOption, dto);
        dto.setSpecOptionId(specOption.getSpecOptionId().getId().toString());
        dto.setId(specOption.getSpecOptionId().getId().toString());
        return dto;
    }
}
