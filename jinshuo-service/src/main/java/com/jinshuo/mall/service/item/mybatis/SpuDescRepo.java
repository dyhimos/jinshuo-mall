package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.desc.SpuDesc;
import com.jinshuo.mall.service.item.mybatis.mapper.SpuDescMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 19458 on 2019/7/18.
 */
@Repository
public class SpuDescRepo {

    @Autowired(required = false)
    private SpuDescMapper mapper;

    public void save(SpuDesc spuDesc){
        mapper.create(spuDesc);
    }

    public SpuDesc findById(Long id){
        return mapper.findById(id);
    }

    public int update(SpuDesc spuDesc){
        return mapper.update(spuDesc);
    }

    public SpuDesc findBySpuId(Long spuId){
        return mapper.findBySpuId(spuId);
    }
}
