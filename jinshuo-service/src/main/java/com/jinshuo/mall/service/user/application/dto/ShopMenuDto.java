package com.jinshuo.mall.service.user.application.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/10/12.
 */
@Data
public class ShopMenuDto {
    private Long shopId;
    private Long menuId;
    private Integer type;
    private String name;
    private Long pid;
    private String code;
    private String desc;
    private List<ShopMenuDto> childs;
}
