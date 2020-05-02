package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.parameter.ParameterValue;
import com.jinshuo.mall.domain.item.parameter.SpuParameter;
import com.jinshuo.mall.domain.item.parameter.SpuParameterId;
import com.jinshuo.mall.service.item.application.cmd.SpuParameterValueDto;
import com.jinshuo.mall.service.item.application.dto.ParameterDto;
import com.jinshuo.mall.service.item.application.dto.ParameterValueDto;
import com.jinshuo.mall.service.item.mybatis.SpuParameterRepo;
import com.jinshuo.mall.service.item.service.query.ParameterQueryService;
import com.jinshuo.mall.service.item.service.query.ParameterValueQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/12/23.
 */
@Slf4j
@Service
public class SpuParameterComService {

    @Autowired
    private SpuParameterRepo spuParameterRepo;

    @Autowired
    private ParameterValueQueryService parameterValueQueryService;

    @Autowired
    private ParameterQueryService parameterQueryService;

    public int save(Long spuId, List<ParameterDto> dtos) {
        if (null == dtos || dtos.size() < 1) {
            return 0;
        }
        List<Long> parameterValueIdsTemp = new ArrayList<>();
        for (ParameterDto parameterDto : dtos) {
            for (ParameterValueDto parameterValueDto : parameterDto.getParams()) {
                parameterValueIdsTemp.add(parameterValueDto.getId());
            }
        }
        spuParameterRepo.deleteBySpuId(spuId);
        SpuParameter spuParameter;
        for (Long id : parameterValueIdsTemp) {
            ParameterValue parameterValue = parameterValueQueryService.getById(id);
            if (null != parameterValue) {
                spuParameter = SpuParameter.builder()
                        .spuParameterId(new SpuParameterId(CommonSelfIdGenerator.generateId()))
                        .parameterId(parameterValue.getParameterId())
                        .parameterValueId(id)
                        .spuId(spuId)
                        .build();
                spuParameterRepo.save(spuParameter);
            }
        }
        return parameterValueIdsTemp.size();
    }

    /*public int saveList(List<SpuParamCmd> cmdList) {
        List<Long> longs = new ArrayList<>();
        cmdList.forEach(spuParamCmd -> longs.add(spuParamCmd.getParameterValueId()));
        save(cmdList.get(0).getSpuId(), longs);
        return cmdList.size();
    }*/

    /**
     * 查询spu参数信息
     *
     * @param spuId
     * @return
     */
    public List<ParameterDto> getBySpuId(Long spuId) {
        List<ParameterDto> dtos = new ArrayList<>();
        List<SpuParameter> spuParameters = spuParameterRepo.findBySpuId(spuId);
        if (null == spuParameters || spuParameters.size() < 1) {
            return null;
        }
        Map<Long, List<SpuParameter>> spuParameterMap = spuParameters.stream().collect(Collectors.groupingBy(SpuParameter::getParameterId));
        ParameterDto parameterDto;
        SpuParameterValueDto spuParameterValueDto;
        for (Long parameterId : spuParameterMap.keySet()) {
            List<SpuParameter> list = spuParameterMap.get(parameterId);
            parameterDto = parameterQueryService.getAllByParameterId(parameterId);
            List<ParameterValueDto> params = new ArrayList<>();
            for (ParameterValueDto param : parameterDto.getParams()) {
                for (SpuParameter spuParameter : list) {
                    if (Long.valueOf(param.getId()).equals(Long.valueOf(spuParameter.getParameterValueId()))) {
                        params.add(param);
                    }
                }
            }
            parameterDto.setParams(params);
            dtos.add(parameterDto);
        }
        return dtos;
    }
}
