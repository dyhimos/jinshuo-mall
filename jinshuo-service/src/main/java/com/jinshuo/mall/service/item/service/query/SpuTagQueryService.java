package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.spu.sputag.SpuTag;
import com.jinshuo.mall.service.item.application.assermbler.SpuTagAssembler;
import com.jinshuo.mall.service.item.application.dto.SpuTagDto;
import com.jinshuo.mall.service.item.mybatis.SpuTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class SpuTagQueryService {

    @Autowired
    private SpuTagRepo spuTagRepo;

    @Autowired
    private TagQueryService tagQueryService;

    public List<SpuTagDto> getBySpuId(Long spuId) {
        List<SpuTag> spuTags = spuTagRepo.queryBySpuId(spuId);
        List<SpuTagDto> dtos = spuTags.stream().map(spuTag -> SpuTagAssembler.assembleSpuTagDto(spuTag)).map(
                spuTagDto -> spuTagDto.changeTagName(tagQueryService.getDtoById(Long.parseLong(spuTagDto.getTagId())))).collect(Collectors.toList());
        return dtos;
    }

}
