package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.parameter.ParameterValue;
import com.jinshuo.mall.service.item.mybatis.mapper.ParameterValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dyh
 */
@Repository
public class ParameterValueRepo {

    @Autowired(required = false)
    private ParameterValueMapper parameterValueMapper;

    public void save(ParameterValue parameterValue) {
        parameterValueMapper.create(parameterValue);
    }

    public void update(ParameterValue parameterValue) {
        parameterValueMapper.update(parameterValue);
    }

    public int delete(Long id) {
        return parameterValueMapper.delete(id);
    }

    public List<ParameterValue> getByParameterId(Long shopId) {
        return parameterValueMapper.queryByParameterId(shopId);
    }

    public ParameterValue getById(Long id) {
        return parameterValueMapper.queryById(id);
    }

    public int deleteByParameterId(Long id) {
        return parameterValueMapper.deleteByParameterId(id);
    }
}
