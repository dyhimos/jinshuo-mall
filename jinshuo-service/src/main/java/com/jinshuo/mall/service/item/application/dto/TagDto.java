package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Data
@Accessors(chain = true)
public class TagDto {
    private String id;
    private String name;
    private Integer sort;
    private Integer goodsCount;
}
