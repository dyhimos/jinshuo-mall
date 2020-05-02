package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.shop.Shop;
import com.jinshuo.mall.service.user.mybatis.mapper.ShopMapper;
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
public class ShopRepo {

    @Autowired(required = false)
    private ShopMapper shopMapper;


    public int save(Shop shop) {
        return shopMapper.create(shop);
    }

    public int update(Shop shop) {
        return shopMapper.update(shop);
    }

    public Shop findById(Long id) {
        return shopMapper.queryById(id);
    }

    public List<Shop> findAll(Shop shop) {
        List<Shop> list = shopMapper.queryList(shop);
        return list;
    }

    public int delete(Long id) {
        return shopMapper.delete(id);
    }

}
