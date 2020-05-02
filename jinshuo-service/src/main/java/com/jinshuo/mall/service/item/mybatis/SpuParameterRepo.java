package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.parameter.SpuParameter;
import com.jinshuo.mall.service.item.mybatis.mapper.SpuParameterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/12/23.
 */
@Repository
public class SpuParameterRepo {

    @Autowired(required = false)
    private SpuParameterMapper spuParameterMapper;

    public int save(SpuParameter spuParameter) {
        return spuParameterMapper.create(spuParameter);
    }

    public int deleteBySpuId(Long spuId) {
        return spuParameterMapper.deleteBySpuId(spuId);
    }

    public List<SpuParameter> findBySpuId(Long spuId) {
        return spuParameterMapper.queryBySpuId(spuId);
    }

}
