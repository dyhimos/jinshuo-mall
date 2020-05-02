package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/10/14.
 */
@Data
public class CategorySpu {
    private String categoryId;
    private String categoryName;
    private List<FrontSpuDto> spus;
}
