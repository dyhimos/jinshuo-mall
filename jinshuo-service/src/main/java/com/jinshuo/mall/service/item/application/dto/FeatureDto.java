package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

/**
 * Created by 19458 on 2019/9/25.
 */
@Data
public class FeatureDto {
    private String id;
    private String name;
    private Integer goodsCount;
    private Integer sort;
    private String shopId;
}
