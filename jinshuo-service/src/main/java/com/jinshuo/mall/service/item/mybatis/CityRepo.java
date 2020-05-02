package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.area.City;
import com.jinshuo.mall.service.item.mybatis.mapper.CityMapper;
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
public class CityRepo {

    @Autowired(required = false)
    private CityMapper cityMapper;

    public int save(City city) {
        return cityMapper.create(city);
    }

    public int update(City city) {
        return cityMapper.update(city);
    }

    public List<City> findAllByShopId(Long shopId) {
        return cityMapper.queryByShopId(shopId);
    }

    public List<City> queryByAreaName(Long shopId, String areaName) {
        return cityMapper.queryByAreaName(shopId, areaName);
    }

    public int delete(Long id) {
        return cityMapper.delete(id);
    }

    public int deleteByShopId(Long shopId) {
        return cityMapper.deleteByShopId(shopId);
    }
}
