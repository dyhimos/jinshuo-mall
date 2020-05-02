package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.ad.AdPosition;
import com.jinshuo.mall.service.item.mybatis.mapper.AdPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class AdPositionRepo {

    @Autowired(required = false)
    private AdPositionMapper adPositionMapper;

    public void save(AdPosition adPosition) {
        adPositionMapper.create(adPosition);
    }

    public void update(AdPosition adPosition) {
        adPositionMapper.update(adPosition);
    }

    public AdPosition queryById(Long adPositionId) {
        return adPositionMapper.queryById(adPositionId);
    }

    public AdPosition queryByCode(String code, Long shopId) {
        return adPositionMapper.queryByCode(code, shopId);
    }

    public List<AdPosition> findAll(AdPosition adPosition) {
        List<AdPosition> list = adPositionMapper.findAll(adPosition);
        return list;
    }

    public int delete(Long id) {
        return adPositionMapper.delete(id);
    }


    public int updateStatus(Long id, Integer adStatus) {
        return adPositionMapper.updateStatus(id, adStatus);
    }
}
