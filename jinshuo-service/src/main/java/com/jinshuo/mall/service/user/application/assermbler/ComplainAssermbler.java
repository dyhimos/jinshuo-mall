package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.cpmplain.Complain;
import com.jinshuo.mall.service.user.application.dto.ComplainDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class ComplainAssermbler {

	/**
     * 转化为列表dto
     * @param complain
     * @return
     */
    public static ComplainDto complainDto(Complain complain) {
        if (null == complain) {
            return null;
        }
        ComplainDto complainDto = new ComplainDto();
        BeanUtils.copyProperties(complain, complainDto);
        complainDto.setId(complain.getComplainId().getId());
        return complainDto;
    }
}

	
