package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.marketing.MarketingProduct;
import com.jinshuo.mall.domain.item.marketing.MarketingProductId;
import com.jinshuo.mall.service.item.application.assermbler.MarketingAssembler;
import com.jinshuo.mall.service.item.application.cmd.MarketingProductCmd;
import com.jinshuo.mall.service.item.application.dto.MarketingProductDto;
import com.jinshuo.mall.service.item.mybatis.MarketingProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class MarketingProdutComService {

    @Autowired
    private MarketingProductRepo marketingProductRepo;


    /**
     * 新增活动产品
     *
     * @param cmds
     */
    @Transactional
    public void create(List<MarketingProductCmd> cmds, Long marketingId) {
        if (null == marketingId) {
            return;
        }
        marketingProductRepo.deleteByMarketingId(marketingId);
        List<MarketingProduct> list = new ArrayList<>();
        MarketingProduct marketingProduct;
        for (MarketingProductCmd cmd : cmds) {
            marketingProduct = MarketingProduct.builder()
                    .marketingProductId(new MarketingProductId(CommonSelfIdGenerator.generateId()))
                    .marketingId(marketingId)
                    .price(cmd.getPrice())
                    .sort(cmd.getSort())
                    .spuId(cmd.getSpuId())
                    .stock(cmd.getStock())
                    .build();
            marketingProduct.insert();
            list.add(marketingProduct);
        }
        marketingProductRepo.save(list);
    }


    /**
     * 删除活动产品
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return marketingProductRepo.delete(id);
    }


    /**
     * 根据活动id查询活动产品
     *
     * @param marketingId
     * @return
     */
    public List<MarketingProductDto> getDtoByMarketingId(Long marketingId) {
        return getByMarketingId(marketingId).stream().map(marketingProduct -> MarketingAssembler.assembleProductDto(marketingProduct)).collect(Collectors.toList());
    }

    /**
     * 根据活动id查询活动产品
     *
     * @param marketingId
     * @return
     */
    public List<MarketingProduct> getByMarketingId(Long marketingId) {
        return marketingProductRepo.findAll(marketingId);
    }

    /**
     * 根据spuId查询活动产品
     *
     * @param spuId
     * @return
     */
    public List<MarketingProduct> getByspuId(Long spuId) {
        return marketingProductRepo.findBySpuId(spuId);
    }

}
