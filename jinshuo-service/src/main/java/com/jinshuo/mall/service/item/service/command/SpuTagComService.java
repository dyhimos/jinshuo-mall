package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.spu.sputag.SpuTag;
import com.jinshuo.mall.domain.item.spu.sputag.SpuTagId;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.domain.item.tag.TagId;
import com.jinshuo.mall.service.item.mybatis.SpuTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class SpuTagComService {

    @Autowired
    private SpuTagRepo spuTagRepo;

    @Transactional
    public int create(List<Long> tagIds, Long spuId) {
        spuTagRepo.deleteBySpuId(spuId);
        if (null == tagIds || tagIds.size() < 1) {
            return 0;
        }
        tagIds.forEach(tagId -> {
            SpuTag spuTag = SpuTag.builder()
                    .spuTagId(new SpuTagId(CommonSelfIdGenerator.generateId()))
                    .spuId(new SpuId(spuId))
                    .tagId(new TagId(tagId))
                    .build();
            spuTag.preInsert();
            spuTagRepo.save(spuTag);
        });
        return tagIds.size();
    }

    public int deleteById(Long spuTagId) {
        return spuTagRepo.deleteById(spuTagId);
    }

    public int deleteBySpuId(Long spuId) {
        return spuTagRepo.deleteBySpuId(spuId);
    }
}
