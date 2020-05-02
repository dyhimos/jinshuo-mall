package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.spec.Spec;
import com.jinshuo.mall.service.item.mybatis.mapper.SpecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by 19458 on 2019/7/17.
 */
@Repository
public class SpecRepo {

    @Autowired
    private SpecMapper specMapper;

    public void save(Spec spec) {
        specMapper.create(spec);
    }

    public Spec findById(Long id) {
        return specMapper.findById(id);
    }

    public Optional<Spec> findOptionalById(Long id) {
        return Optional.ofNullable(specMapper.findById(id));
    }

    public void update(Spec spec) {
        specMapper.update(spec);
    }

    public List<Spec> findAll(Long shopId){return specMapper.findAll(shopId);}

    public void delete(Long specId) {
        specMapper.delete(specId);
    }
}
