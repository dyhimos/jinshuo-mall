package com.jinshuo.mall.service.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/4/2.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsUserDto {
    private Integer accessCount;//浏览量
    private Integer accessMember;//访客数
    private double averagePages;//平均访问页数

    private Integer registerMember;//注册数
    private String conversionRate;//转换率

    public void init() {
        this.accessCount = 0;
        this.accessMember = 0;
        this.averagePages = 0;
        this.registerMember = 0;
        this.conversionRate = "0.0%";
    }
}
