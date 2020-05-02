package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.sku.SkuOption;
import com.jinshuo.mall.service.item.mybatis.mapper.SkuOptionMapper;
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
public class SkuOptionRepo  {

    @Autowired(required = false)
    private SkuOptionMapper spuMapper;

    public void save(SkuOption skuOption) {
        spuMapper.create(skuOption);
    }

    public void deleteBySkuId(Long skuId) {
        spuMapper.deleteBySkuId(skuId);
    }

    public List<SkuOption> queryBySkuId(Long skuId) {
        return spuMapper.queryBySkuId(skuId);
    }
}
