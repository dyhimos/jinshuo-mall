package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.sku.SkuOption;
import com.jinshuo.mall.service.item.application.dto.SkuOptionDto;
import com.jinshuo.mall.service.item.mybatis.SkuOptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class SkuOptionQueryService {

    @Autowired
    private SkuOptionRepo skuOptionRepo;

    @Autowired
    private SpecOptionQueryService specOptionQueryService;

    public List<SkuOptionDto> getBySkuId(Long skuId) {
        List<SkuOption> skuOptions = skuOptionRepo.queryBySkuId(skuId);
        return skuOptions.stream().map(
                skuOption -> {
                    SkuOptionDto dto = specOptionQueryService.getDtoByOptionId(skuOption.getSpecOptionId().getId());
                    dto.setId(skuOption.getSkuOptionId().getId().toString());
                    dto.setSort(skuOption.getSort());
                    return dto;
                }
        ).collect(Collectors.toList());
    }
}
