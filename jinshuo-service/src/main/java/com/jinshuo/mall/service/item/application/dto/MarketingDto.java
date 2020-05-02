package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/10/16.
 */
@Data
public class MarketingDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;
    private Integer marketingType;//活动类型 1->秒杀
    private String name;
    private Date startTime;
    private Date endTime;
    private Integer goodsMode; //商品范围 0所有 1部分
    private Integer quantity;//限购数量 0为不限购
    private Integer sort;
    private String desc;
    private Integer marketingStatus;//活动状态 0正常 1停用
    private Date createDate;
    private List<MarketingProductDto> list;
}
