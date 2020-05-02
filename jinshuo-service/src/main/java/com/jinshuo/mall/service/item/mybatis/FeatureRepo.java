package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.feature.Feature;
import com.jinshuo.mall.service.item.mybatis.mapper.FeatureMapper;
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
public class FeatureRepo {

    @Autowired(required = false)
    private FeatureMapper featureMapper;

    public int save(Feature feature) {
        return featureMapper.create(feature);
    }

    public int update(Feature feature) {
        return featureMapper.update(feature);
    }

    public Feature queryById(Long featureId) {
        return featureMapper.queryById(featureId);
    }

    public List<Feature> findAll(Long shopId) {
        return featureMapper.findAll(shopId);
    }

    public int delete(Long tagId) {
        return featureMapper.delete(tagId);
    }

}
