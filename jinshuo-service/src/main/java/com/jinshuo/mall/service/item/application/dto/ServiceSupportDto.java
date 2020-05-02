package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

/**
 * Created by 19458 on 2019/9/30.
 */
@Data
public class ServiceSupportDto {
    private String id;
    private String shopId;
    private String name;
    private String desc;
    private Integer sort;
    private Integer ssStatus;
}
