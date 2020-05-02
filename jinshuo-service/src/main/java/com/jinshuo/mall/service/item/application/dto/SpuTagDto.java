package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

/**
 * Created by 19458 on 2019/7/24.
 */
@Data
public class SpuTagDto {
    private String id;
    private String tagId;
    private String tagName;
    private String spuId;

    public SpuTagDto changeTagName(TagDto tagDto) {
        if (null == tagDto) {
            return this;
        }
        this.tagName = tagDto.getName();
        return this;
    }
}
