package com.jinshuo.mall.service.item.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/10/28.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitysCmd {
    private Long areaId;
    private String areaProName;
    private String areaName;
    private String areaCode;
}
