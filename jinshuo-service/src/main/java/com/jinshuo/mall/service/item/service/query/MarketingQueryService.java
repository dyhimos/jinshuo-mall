package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.marketing.Marketing;
import com.jinshuo.mall.service.item.application.assermbler.MarketingAssembler;
import com.jinshuo.mall.service.item.application.dto.MarketingDto;
import com.jinshuo.mall.service.item.application.dto.MarketingProductDto;
import com.jinshuo.mall.service.item.application.qry.MarketingQry;
import com.jinshuo.mall.service.item.mybatis.MarketingRepo;
import com.jinshuo.mall.service.item.service.command.MarketingProdutComService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Data
@Service
public class MarketingQueryService {

    @Autowired
    private MarketingRepo marketingRepo;

    @Autowired
    private MarketingProdutComService marketingProdutComService;


    public MarketingDto getById(Long marketingId) {
        MarketingDto dto = MarketingAssembler.assembleDto(marketingRepo.queryById(marketingId));
        List<MarketingProductDto> list = marketingProdutComService.getDtoByMarketingId(marketingId);
        if (null != dto && null != list) {
            dto.setList(list);
        }
        return dto;
    }


    public PageInfo getPageInfo(MarketingQry qry) {
        if (null == qry.getShopId()) {
            qry.setShopId(10088l);
        }
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        Marketing temp = new Marketing();
        temp.setShopId(qry.getShopId());
        List<Marketing> list = marketingRepo.findAll(temp);
        PageInfo pageInfo = new PageInfo(list);
        List<MarketingDto> dtos = list.stream().map(marketing ->
                MarketingAssembler.assembleDto(marketing))
                .collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }


}
