package com.jinshuo.mall.service.item.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by 19458 on 2019/10/28.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityCmd {
    private Long shopId;
    private String openBusiness;
    private List<CitysCmd> citysCmdList;
}