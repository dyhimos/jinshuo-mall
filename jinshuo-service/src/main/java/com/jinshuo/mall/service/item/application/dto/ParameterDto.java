package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by 19458 on 2019/11/18.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ParameterDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;//店铺id
    private String name;// 参数名称
    private List<Integer> type; // 0-> 填充式模板  1-> 固定模板
    private Integer sort;
    private Integer singleFlag;

    private List<ParameterValueDto> params;

}
