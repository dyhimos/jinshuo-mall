package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.parameter.ParameterValue;
import com.jinshuo.mall.service.item.application.assermbler.ParameterAssembler;
import com.jinshuo.mall.service.item.application.dto.ParameterValueDto;
import com.jinshuo.mall.service.item.mybatis.ParameterValueRepo;
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
public class ParameterValueQueryService {

    @Autowired
    private ParameterValueRepo parameterValueRepo;

    public List<ParameterValueDto> getByParameterId(Long parameterId){
       List<ParameterValue> parameterValues = parameterValueRepo.getByParameterId(parameterId);
       List<ParameterValueDto> dtos = parameterValues
               .stream()
               .map(parameterValue -> ParameterAssembler.assembleValueDto(parameterValue))
               .collect(Collectors.toList());
        return dtos;
    }

    public ParameterValue getById(Long id) {
        return parameterValueRepo.getById(id);
    }
}
