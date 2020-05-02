package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.spec.Spec;
import com.jinshuo.mall.service.item.application.dto.SpecDto;
import com.jinshuo.mall.service.item.application.dto.UserSpecDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/17.
 */
public class SpecAssembler {

    /**
     * @param spec
     * @return
     */
    public static SpecDto assembleSpecDto(Spec spec) {
        if (spec == null) {
            return null;
        }
        SpecDto dto = new SpecDto();
        BeanUtils.copyProperties(spec, dto);
        dto.setSpecId(spec.getSpecId().getId().toString());
        return dto;
    }


    /**
     * @param spec
     * @return
     */
    public static UserSpecDto assembleUserDto(Spec spec) {
        if (spec == null) {
            return null;
        }
        UserSpecDto dto = new UserSpecDto();
        BeanUtils.copyProperties(spec, dto);
        dto.setSpecId(spec.getSpecId().getId().toString());
        return dto;
    }
}
