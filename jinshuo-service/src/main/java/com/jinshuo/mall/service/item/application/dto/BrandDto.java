package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

/**
 * Created by 19458 on 2019/7/22.
 */
@Data
public class BrandDto {
    private String id;
    private String name;
    private Integer sort;
    private Integer itemNum;
    private String pictureUrl;
}
