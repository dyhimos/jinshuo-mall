package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.parameter.Parameter;
import com.jinshuo.mall.service.item.application.assermbler.ParameterAssembler;
import com.jinshuo.mall.service.item.application.dto.ParameterDto;
import com.jinshuo.mall.service.item.mybatis.ParameterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/11/18.
 */
@Slf4j
@Service
public class ParameterQueryService {

    @Autowired
    private ParameterRepo parameterRepo;

    @Autowired
    private ParameterValueQueryService parameterValueQueryService;

    public List<ParameterDto> getAllByShopId(Long shopId) {
        List<Parameter> parameters = parameterRepo.getByShopId(shopId);
        List<ParameterDto> dtos = parameters.stream().map(parameter -> ParameterAssembler.assembleDto(parameter))
                .map(parameterDto -> {
                    parameterDto.setParams(parameterValueQueryService.getByParameterId(parameterDto.getId()));
                    return parameterDto;
                })
                .collect(Collectors.toList());
        return dtos;
    }

    public ParameterDto getAllByParameterId(Long parameterId) {
        Parameter parameter = parameterRepo.getById(parameterId);
        ParameterDto dto = ParameterAssembler.assembleDto(parameter);
        dto.setParams(parameterValueQueryService.getByParameterId(dto.getId()));
        return dto;
    }
}
