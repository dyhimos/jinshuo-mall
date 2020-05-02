package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.album.Album;
import com.jinshuo.mall.service.item.application.qry.AlbumQry;
import com.jinshuo.mall.service.item.mybatis.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class AlbumQueryService {

    @Autowired
    private AlbumRepo repo;


    public Album getById(Long albumId) {
        return repo.queryById(albumId);
    }


    public Optional<Album> getOptionById(Long albumId) {
        return Optional.ofNullable(getById(albumId));
    }

    public List<Album> getAlbumsBySpuId(Long spuId) {
        return repo.queryBySpuId(spuId);
    }

    public List<String> getUrlsBySpuId(Long spuId) {
        List<Album> list = getAlbumsBySpuId(spuId);
        if (null == list || list.size() < 1) {
            return null;
        }
        List<String> urls = list.stream()
                .map(album -> {
                    return album.getUrl();
                }).collect(Collectors.toList());
        return urls;
    }


    public List<Album> getAlbumsByExample(AlbumQry qry) {
        return repo.queryByExample(qry);
    }


}
