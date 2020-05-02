package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.sku.Sku;
import com.jinshuo.mall.service.item.mybatis.mapper.SkuMapper;
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
public class SkuRepo {

    @Autowired(required = false)
    private SkuMapper skuMapper;

    public void save(Sku sku) {
        skuMapper.create(sku);
    }

    public void update(Sku sku) {
        skuMapper.update(sku);
    }

    public void delete(Long spuId) {
        skuMapper.deleteBySpuId(spuId);
    }

    public List<Sku> queryBySpuId(Long spuId) {
        return skuMapper.queryBySpuId(spuId);
    }

    public Sku queryBySkuId(Long skuId) {
        return skuMapper.queryBySkuId(skuId);
    }

    public int updateStock(Sku sku) {
        return skuMapper.updateStock(sku);
    }

    public void deleteById(Long skuId) {
        skuMapper.deleteById(skuId);
    }
}
