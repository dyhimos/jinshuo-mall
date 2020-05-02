package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.album.Album;
import com.jinshuo.mall.service.item.application.qry.AlbumQry;
import com.jinshuo.mall.service.item.mybatis.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2019/7/17.
 */
@Repository
public class AlbumRepo {

    @Autowired(required = false)
    private AlbumMapper mapper;

    public void save(Album album) {
        mapper.create(album);
    }

    public void update(Album album) {
        mapper.update(album);
    }

    public Album queryById(Long albumId) {
        return mapper.queryById(albumId);
    }

    public List<Album> queryBySkuId(Long skuId) {
        return mapper.queryBySkuId(skuId);
    }

    public List<Album> queryBySpuId(Long spuId) {
        return mapper.queryBySpuId(spuId);
    }


    public List<Album> queryByExample(AlbumQry qry) {
        return mapper.queryByExample(qry);
    }

    public int deleteBySpuId(Long spuId){
        return mapper.deleteBySpuId(spuId);
    }
}
