package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.spuOther.SpuOther;
import com.jinshuo.mall.service.item.application.assermbler.SpuOtherAssembler;
import com.jinshuo.mall.service.item.application.dto.SpuOtherDto;
import com.jinshuo.mall.service.item.mybatis.SpuOtherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class SpuOtherQueryService {

    @Autowired
    private SpuOtherRepo spuOtherRepo;

    public SpuOther getById(Long id) {
        return spuOtherRepo.queryById(id);
    }

    public Optional<SpuOther> getOptionById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    public List<SpuOtherDto> getAll() {
        List<SpuOther> list = spuOtherRepo.findAll();
        List<SpuOtherDto> dtos = list.stream()
                .map(spuOther -> SpuOtherAssembler.assembleSpuOtherDto(spuOther)).collect(Collectors.toList());
        return dtos;
    }

    public SpuOtherDto getBySpuId(Long id) {
        return SpuOtherAssembler.assembleSpuOtherDto(spuOtherRepo.queryBySpuId(id));
    }

    public SpuOther findBySpuId(Long id) {
        return spuOtherRepo.queryBySpuId(id);
    }

    public Optional<SpuOther> findOptionalBySpuId(Long id) {
        return Optional.ofNullable(spuOtherRepo.queryBySpuId(id));
    }



}
