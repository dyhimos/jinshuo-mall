package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/9/10.
 */
@Data
public class AdvertisementDto {
    private String id;
    private String adPositionId;
    private String titile;
    private String image;
    private String url;
    private Integer sort;
    private Integer isEnabled;//是否可用 0->可用 1->不可用
    private List<String> areaNames;//地区
}
