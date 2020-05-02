package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.spec.Spec;
import com.jinshuo.mall.domain.item.spec.SpecOption;
import com.jinshuo.mall.service.item.application.assermbler.SpecOptionAssembler;
import com.jinshuo.mall.service.item.application.dto.SkuOptionDto;
import com.jinshuo.mall.service.item.application.dto.SpecOptionDto;
import com.jinshuo.mall.service.item.mybatis.SpecOptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/17.
 */
@Service
public class SpecOptionQueryService {

    @Autowired
    private SpecOptionRepo specOptionRepo;

    @Autowired
    private SpecQueryService specQueryService;

    public List<SpecOption> getBySpecId(Long specId) {
        return specOptionRepo.findBySpecId(specId);
    }

    public SpecOptionDto getDtoById(Long id) {
        return SpecOptionAssembler.assembleSpecOptionDto(specOptionRepo.queryById(id));
    }

    public List<SpecOptionDto> getDtoBySpecId(Long specId) {
        return specOptionRepo.findBySpecId(specId).stream().map(specOption ->
                SpecOptionAssembler.assembleSpecOptionDto(specOption)
        ).collect(Collectors.toList());
    }

    public SkuOptionDto getDtoByOptionId(Long specOptionId) {
        SpecOption specOption = specOptionRepo.queryById(specOptionId);
        Spec spec = specQueryService.findById(specOption.getSpecId().getId());
        SkuOptionDto dto = SkuOptionDto.builder()
                .specId(specOption.getSpecId().getId().toString())
                .specName(spec.getName())
                .optionId(specOption.getSpecOptionId().getId().toString())
                .optionName(specOption.getName())
                .build();
        return dto;
    }

    public SpecOption findById(Long specOptionId){
        return specOptionRepo.queryById(specOptionId);
    }

    public Optional<SpecOption> findOptionalById(Long specOptionId){
        return Optional.ofNullable(findById(specOptionId));
    }
}
