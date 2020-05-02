package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.album.Album;
import com.jinshuo.mall.domain.item.album.AlbumId;
import com.jinshuo.mall.domain.item.sku.SkuId;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.service.item.application.cmd.AlbumCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.AlbumUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.AlbumRepo;
import com.jinshuo.mall.service.item.service.query.AlbumQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class AlbumComService {

    @Autowired
    private AlbumRepo repo;

    @Autowired
    private AlbumQueryService albumQueryService;

    /**
     * 新增商品媒体
     *
     * @param cmd
     */
    public void create(AlbumCreateCmd cmd) {
        Album album = Album.builder()
                .albumId(new AlbumId(CommonSelfIdGenerator.generateId()))
                .skuId(new SkuId(cmd.getSkuId()))
                .spuId(new SpuId(cmd.getSpuId()))
                .type(cmd.getType())
                .sort(cmd.getSort())
                .url(cmd.getUrl())
                .build();
        album.preInsert();
        repo.save(album);
    }


    /**
     * 修改商品媒体
     *
     * @param cmd
     */
    public void update(AlbumUpdateCmd cmd) {
        albumQueryService.getOptionById(cmd.getId()).map(
                album -> album.update(cmd.getUrl(), cmd.getSort(), cmd.getType())
        ).ifPresent(album -> repo.update(album));
    }

    public int add(Long spuId, List<String> urls) {
        repo.deleteBySpuId(spuId);
        urls.forEach(url -> {
            Album album = Album.builder()
                    .albumId(new AlbumId(CommonSelfIdGenerator.generateId()))
                    .spuId(new SpuId(spuId))
                    .type(1)
                    .url(url)
                    .build();
            album.preInsert();
            repo.save(album);
        });
        return 0;
    }
}
