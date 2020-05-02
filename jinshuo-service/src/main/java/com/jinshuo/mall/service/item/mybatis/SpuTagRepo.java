package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.spu.sputag.SpuTag;
import com.jinshuo.mall.service.item.mybatis.mapper.SpuTagMapper;
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
public class SpuTagRepo {

    @Autowired(required = false)
    private SpuTagMapper spuTagMapper;

    public void save(SpuTag spuTag) {
        spuTagMapper.create(spuTag);
    }

    public SpuTag queryById(Long spuTagId) {
        return spuTagMapper.queryById(spuTagId);
    }

    public List<SpuTag> queryBySpuId(Long spuId) {
        return spuTagMapper.queryBySpuId(spuId);
    }

    public int deleteById(Long spuTagId) {
        return spuTagMapper.deleteById(spuTagId);
    }

    public int deleteBySpuId(Long spuTagId) {
        return spuTagMapper.deleteBySpuId(spuTagId);
    }
}
