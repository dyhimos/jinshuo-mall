package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.parameter.Parameter;
import com.jinshuo.mall.domain.item.parameter.ParameterValue;
import com.jinshuo.mall.domain.item.parameter.ParameterValueId;
import com.jinshuo.mall.service.item.application.cmd.ParameterValueCmd;
import com.jinshuo.mall.service.item.mybatis.ParameterValueRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class ParameterValueComService {

    @Autowired
    private ParameterValueRepo parameterValueRepo;


    /**
     * 新增参数
     *
     * @param cmd
     */
    public ParameterValue create(ParameterValueCmd cmd) {
        log.info(" -- 新增参数,{}",cmd);
        if (null == cmd.getParameterId()) {
            return null;
        }
        ParameterValue parameterValue = ParameterValue.builder()
                .parameterValueId(new ParameterValueId(CommonSelfIdGenerator.generateId()))
                .param(cmd.getParam())
                .name(cmd.getName())
                .type(cmd.getType())
                .sort(cmd.getSort())
                .parameterId(cmd.getParameterId())
                .build();
        parameterValue.preInsert();
        parameterValueRepo.save(parameterValue);
        return parameterValue;
    }


    /**
     * 批量保存
     *
     * @param cmds
     * @param parameter
     */
    public void createList(List<ParameterValueCmd> cmds, Parameter parameter) {
        log.info(" -- 批量保存参数值,{}",cmds);
        if (null == parameter.getParameterId().getId()) {
            return;
        }
        int count = parameterValueRepo.deleteByParameterId(parameter.getParameterId().getId());
        for (ParameterValueCmd cmd : cmds) {
            ParameterValue parameterValue = ParameterValue.builder()
                    .parameterValueId(new ParameterValueId(CommonSelfIdGenerator.generateId()))
                    .param(cmd.getParam())
                    .name(cmd.getName())
                    .type(cmd.getType())
                    .sort(cmd.getSort())
                    .parameterId(parameter.getParameterId().getId())
                    .build();
            parameterValue.preInsert();
            parameterValueRepo.save(parameterValue);
        }
    }

    public int delete(Long id){
        return parameterValueRepo.delete(id);
    }

}
