package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.ad.Advertisement;
import com.jinshuo.mall.service.item.mybatis.mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname AdPositionRepo
 * @Date 2019/9/10 20:07
 * @Created by dyh
 */
@Repository
public class AdvertisementRepo {

    @Autowired(required = false)
    private AdvertisementMapper advertisementMapper;


    public void save(Advertisement advertisement) {
        advertisementMapper.create(advertisement);
    }

    public void update(Advertisement advertisement) {
        advertisementMapper.update(advertisement);
    }

    public Advertisement queryById(Long advertisementId) {
        return advertisementMapper.queryById(advertisementId);
    }


    public List<Advertisement> findAll() {
        List<Advertisement> list = advertisementMapper.findAll();
        return list;
    }

    public int delete(Long id) {
        return advertisementMapper.delete(id);
    }

    public List<Advertisement> getByPage() {
        List<Advertisement> list = advertisementMapper.findAll();
        return list;
    }

    public List<Advertisement> findByPositionId(Long adPositionId) {
        return advertisementMapper.queryByPositionId(adPositionId);
    }

    public List<Advertisement> queryByUsedPositionId(Advertisement advertisement) {
        return advertisementMapper.queryByUsedPositionId(advertisement);
    }
}
