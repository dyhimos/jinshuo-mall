package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.spushop.SpuShop;
import com.jinshuo.mall.service.item.mybatis.mapper.SpuShopMapper;
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
public class SpuShopRepo {

    @Autowired(required = false)
    private SpuShopMapper spuShopMapper;

    public void save(SpuShop spuShop) {
        spuShopMapper.create(spuShop);
    }

    public List<SpuShop> findAll(Long shoId) {
        List<SpuShop> list = spuShopMapper.queryByShopId(shoId);
        return list;
    }

    public SpuShop findByShopIdSpuId(Long shoId,Long spuId) {
        SpuShop list = spuShopMapper.findByShopIdSpuId(shoId,spuId);
        return list;
    }

    public int delete(Long spuShopId) {
        return spuShopMapper.delete(spuShopId);
    }
}
