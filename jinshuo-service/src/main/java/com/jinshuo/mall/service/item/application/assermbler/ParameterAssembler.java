package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.parameter.Parameter;
import com.jinshuo.mall.domain.item.parameter.ParameterValue;
import com.jinshuo.mall.service.item.application.dto.ParameterDto;
import com.jinshuo.mall.service.item.application.dto.ParameterValueDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/11/18.
 */
public class ParameterAssembler {


    /**
     * @param parameter
     * @return
     */
    public static ParameterDto assembleDto(Parameter parameter) {
        if (null == parameter) {
            return null;
        }
        ParameterDto dto = new ParameterDto();
        BeanUtils.copyProperties(parameter, dto);
        dto.setId(parameter.getParameterId().getId());
        if (StringUtils.isNotBlank(parameter.getType())) {
            List<String> strings = Arrays.asList(parameter.getType().split(","));
            List<Integer> integers = strings.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
            dto.setType(integers);
        }else{
            dto.setType(new ArrayList<>());
        }
        return dto;
    }


    /**
     * @param parameterValue
     * @return
     */
    public static ParameterValueDto assembleValueDto(ParameterValue parameterValue) {
        if (null == parameterValue) {
            return null;
        }
        ParameterValueDto dto = new ParameterValueDto();
        BeanUtils.copyProperties(parameterValue, dto);
        dto.setId(parameterValue.getParameterValueId().getId());
        return dto;
    }
}
