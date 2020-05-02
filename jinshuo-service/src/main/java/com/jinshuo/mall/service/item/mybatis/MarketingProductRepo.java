package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.marketing.MarketingProduct;
import com.jinshuo.mall.service.item.mybatis.mapper.MarketingProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class MarketingProductRepo {

    @Autowired(required = false)
    private MarketingProductMapper marketingProductMapper;

    public void save(List<MarketingProduct> list) {
        marketingProductMapper.create(list);
    }

    public List<MarketingProduct> findAll(Long marketingId) {
        return marketingProductMapper.findAll(marketingId);
    }

    public List<MarketingProduct> findBySpuId(Long marketingId) {
        return marketingProductMapper.queryBySpuId(marketingId);
    }

    public int delete(Long id) {
        return marketingProductMapper.delete(id);
    }

    public int deleteByMarketingId(Long marketingId) {
        return marketingProductMapper.deleteByMarketingId(marketingId);
    }

}
