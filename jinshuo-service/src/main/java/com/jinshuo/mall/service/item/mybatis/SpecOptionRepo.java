package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.spec.SpecOption;
import com.jinshuo.mall.service.item.mybatis.mapper.SpecOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/7/17.
 */
@Repository
public class SpecOptionRepo {

    @Autowired(required = false)
    private SpecOptionMapper mapper;

    public void save(SpecOption specOption) {
        mapper.create(specOption);
    }

    public void update(SpecOption specOption) {
        mapper.update(specOption);
    }

    public int deleteBySpecId(Long specId) {
        return mapper.deleteBySpecId(specId);
    }

    public List<SpecOption> findBySpecId(Long specId){return mapper.findBySpecId(specId);}

    public SpecOption queryById(Long specOptionId){
        return mapper.queryById(specOptionId);
    }

    public int deleteById(Long specOptionId) {
        return mapper.deleteById(specOptionId);
    }
}
