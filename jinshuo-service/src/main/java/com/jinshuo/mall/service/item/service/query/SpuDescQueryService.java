package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.desc.SpuDesc;
import com.jinshuo.mall.service.item.application.assermbler.SpuDescAssembler;
import com.jinshuo.mall.service.item.application.dto.SpuDescDto;
import com.jinshuo.mall.service.item.mybatis.SpuDescRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by 19458 on 2019/7/18.
 */
@Service
public class SpuDescQueryService {

    @Autowired
    private SpuDescRepo spuDescRepo;

    public SpuDesc findById(Long id) {
        return spuDescRepo.findById(id);
    }

    public Optional<SpuDesc> findOptionById(Long id) {
        return Optional.ofNullable(findById(id));
    }

    public SpuDescDto findBySpuId(Long spuId) {
        return SpuDescAssembler.assembleSpuDto(spuDescRepo.findBySpuId(spuId));
    }

    public SpuDesc findSpuDescBySpuId(Long spuId){
        return spuDescRepo.findBySpuId(spuId);
    }
}
