package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.parameter.Parameter;
import com.jinshuo.mall.service.item.mybatis.mapper.ParameterMapper;
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
public class ParameterRepo {

    @Autowired(required = false)
    private ParameterMapper parameterMapper;

    public void save(Parameter parameter) {
        parameterMapper.create(parameter);
    }

    public void update(Parameter parameter) {
        parameterMapper.update(parameter);
    }

    public int delete(Long id) {
        return parameterMapper.delete(id);
    }

    public List<Parameter> getByShopId(Long shopId) {
        return parameterMapper.queryByShopId(shopId);
    }

    public Parameter getById(Long id) {
        return parameterMapper.queryById(id);
    }
}
