package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.sku.Sku;
import com.jinshuo.mall.domain.item.sku.SkuId;
import com.jinshuo.mall.domain.item.sku.SkuOption;
import com.jinshuo.mall.domain.item.sku.SkuOptionId;
import com.jinshuo.mall.domain.item.spec.SpecId;
import com.jinshuo.mall.domain.item.spec.SpecOption;
import com.jinshuo.mall.domain.item.spec.SpecOptionId;
import com.jinshuo.mall.service.item.application.cmd.SpecOptionIdCmd;
import com.jinshuo.mall.service.item.application.dto.SpecOptionDto;
import com.jinshuo.mall.service.item.application.dto.UserSpecDto;
import com.jinshuo.mall.service.item.mybatis.SkuOptionRepo;
import com.jinshuo.mall.service.item.service.query.SkuQueryService;
import com.jinshuo.mall.service.item.service.query.SpecOptionQueryService;
import com.jinshuo.mall.service.item.service.query.SpecQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/18.
 */
@Service
public class SkuOptionComService {

    @Autowired
    private SkuOptionRepo repo;

    @Autowired
    private SpecOptionQueryService specOptionQueryService;

    @Autowired
    private SkuQueryService skuQueryService;

    @Autowired
    private SpecQueryService specQueryService;


    /**
     * 保存商品SKU关联规格参数
     *
     * @param cmds
     * @param skuId
     */
    @Transactional
    public void create(List<SpecOptionIdCmd> cmds, SkuId skuId) {
        if (null == cmds || cmds.size() < 1) {
            return;
        }
        SpecOption specOption;
        for (SpecOptionIdCmd cmd : cmds) {
            specOption = specOptionQueryService.findById(cmd.getId());
            SkuOption skuOption = SkuOption.builder()
                    .skuOptionId(new SkuOptionId(CommonSelfIdGenerator.generateId()))
                    .skuId(skuId)
                    .specId(specOption.getSpecId())
                    .specOptionId(new SpecOptionId(cmd.getId()))
                    .sort(cmd.getSort())
                    .build();
            skuOption.insert();
            repo.save(skuOption);
        }
    }

    /**
     * 根据skuId删除商品SKU关联规格参数
     *
     * @param skuId
     */
    @Transactional
    public void deleteBySkuId(Long skuId) {
        repo.deleteBySkuId(skuId);
    }

    /**
     * 根据产品id获取产品规格
     *
     * @param spuId
     * @return
     */
    public List<UserSpecDto> getBySpuId(Long spuId) {
        List<Sku> skus = skuQueryService.getSkusBySpuId(spuId);
        List<SkuOption> skuOptions = new ArrayList<>();
        List<SkuOption> temp;
        for (Sku sku : skus) {
            temp = repo.queryBySkuId(sku.getSkuId().getId());
            skuOptions.addAll(temp);
        }

        Map<SpecId, List<SkuOption>> specIdListMap = skuOptions.stream().collect(Collectors.groupingBy(SkuOption::getSpecId));
        List<UserSpecDto> userSpecDtos = new ArrayList<>();
        UserSpecDto userSpecDto;
        for (SpecId key : specIdListMap.keySet()) {
            userSpecDto = specQueryService.getUserDtoById(key.getId());
            List<SkuOption> skuOptions1 = specIdListMap.get(key);
            List<SpecOptionDto> specOptionDtos = new ArrayList<>();
            for (SkuOption skuOption : skuOptions1) {
                SpecOptionDto specOptionDto = specOptionQueryService.getDtoById(skuOption.getSpecOptionId().getId());
                if (specOptionDtos.contains(specOptionDto)) {
                    continue;
                }
                specOptionDtos.add(specOptionDto);
            }
            userSpecDto.setOptions(specOptionDtos);
            userSpecDtos.add(userSpecDto);
        }
        return userSpecDtos;
    }
}
