package com.jinshuo.mall.service.item.application.dto;

import com.jinshuo.mall.service.item.application.cmd.SpuParameterValueDto;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2020/1/2.
 */
@Data
public class SpuParameterDto {
    private Long parameterId;
    private String name;
    private Integer singleFlag;
    private List<Integer> type;
    private List<SpuParameterValueDto> values;
}
