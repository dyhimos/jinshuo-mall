package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/10/30.
 */
@Data
public class CityMainDto {
    private String areaProName;
    private List<CityDto> cityDtos;
}
