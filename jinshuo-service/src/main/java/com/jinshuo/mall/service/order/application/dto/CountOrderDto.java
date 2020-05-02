package com.jinshuo.mall.service.order.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2020/4/7 0007.
 */
@Data
public class CountOrderDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private Integer orderCount;
    private Integer payCount;
}
