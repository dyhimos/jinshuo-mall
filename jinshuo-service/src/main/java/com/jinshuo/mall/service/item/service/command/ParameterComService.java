package com.jinshuo.mall.service.item.service.command;

import com.google.common.base.Joiner;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.parameter.Parameter;
import com.jinshuo.mall.domain.item.parameter.ParameterId;
import com.jinshuo.mall.service.item.application.cmd.ParameterCmd;
import com.jinshuo.mall.service.item.mybatis.ParameterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class ParameterComService {

    @Autowired
    private ParameterRepo parameterRepo;

    @Autowired
    private ParameterValueComService parameterValueComService;


    /**
     * 新增参数
     *
     * @param cmd
     */
    public void create(ParameterCmd cmd) {
        log.info(" -- 新增参数,{}", cmd);
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        if (null != cmd.getId()) {
            update(cmd);
            return;
        }
        Parameter parameter = Parameter.builder()
                .parameterId(new ParameterId(CommonSelfIdGenerator.generateId()))
                .shopId(cmd.getShopId())
                .sort(cmd.getSort())
                .type(Joiner.on(",").join(cmd.getType()))
                .name(cmd.getName())
                .singleFlag(cmd.getSingleFlag())
                .build();
        parameter.preInsert();
        parameterRepo.save(parameter);
        if (null != cmd.getParams() && cmd.getParams().size() > 0) {
            parameterValueComService.createList(cmd.getParams(), parameter);
        }
    }

    /**
     * 修改参数
     *
     * @param cmd
     */
    public void update(ParameterCmd cmd) {
        log.info(" -- 修改参数,{}", cmd);
        Parameter parameter = parameterRepo.getById(cmd.getId());
        if (null != parameter) {
            parameter.update(cmd.getName(), cmd.getType(), cmd.getSort(), cmd.getSingleFlag());
            parameterRepo.update(parameter);
            //parameterValueComService.createList(cmd.getParams(), parameter);
        }
    }

    public int delete(Long id) {
        return parameterRepo.delete(id);
    }
}
