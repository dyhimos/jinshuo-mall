package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2020/4/6.
 */
@Data
public class VisitorListDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private Integer accessCount;//浏览量
    private Integer accessMember;//访客数
    private double averagePages;//平均访问页数

    private Integer registerMember;//注册数
    private String conversionRate;//转换率
}
