package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.tag.Tag;
import com.jinshuo.mall.service.item.application.dto.TagDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class TagAssembler {
    /**
     * @param tag
     * @return
     */
    public static TagDto assembleTagDto(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagDto dto = new TagDto();
        BeanUtils.copyProperties(tag, dto);
        dto.setId(tag.getTagId().getId().toString());
        return dto;
    }
}
