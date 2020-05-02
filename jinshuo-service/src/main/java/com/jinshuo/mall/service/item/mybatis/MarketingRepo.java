package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.marketing.Marketing;
import com.jinshuo.mall.service.item.mybatis.mapper.MarketingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class MarketingRepo {

    @Autowired(required = false)
    private MarketingMapper marketingMapper;

    public void save(Marketing marketing) {
        marketingMapper.create(marketing);
    }

    public void update(Marketing marketing) {
        marketingMapper.update(marketing);
    }

    public Marketing queryById(Long marketingId) {
        return marketingMapper.queryById(marketingId);
    }

    public List<Marketing> findAll(Marketing marketing) {
        return marketingMapper.findAll(marketing);
    }

    public int delete(Long id) {
        return marketingMapper.delete(id);
    }

    public int updateStatus(Long id, Integer adStatus) {
        return marketingMapper.updateStatus(id, adStatus);
    }
}
