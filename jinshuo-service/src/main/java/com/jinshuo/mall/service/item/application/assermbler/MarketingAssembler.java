package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.marketing.Marketing;
import com.jinshuo.mall.domain.item.marketing.MarketingProduct;
import com.jinshuo.mall.service.item.application.dto.MarketingDto;
import com.jinshuo.mall.service.item.application.dto.MarketingProductDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by dyh on 2019/7/22.
 */
public class MarketingAssembler {

    /**
     * @param marketing
     * @return
     */
    public static MarketingDto assembleDto(Marketing marketing) {
        if (null == marketing) {
            return null;
        }
        MarketingDto dto = new MarketingDto();
        BeanUtils.copyProperties(marketing, dto);
        dto.setId(marketing.getMarketingId().getId());
        return dto;
    }

    /**
     * @param marketingProduct
     * @return
     */
    public static MarketingProductDto assembleProductDto(MarketingProduct marketingProduct) {
        if (null == marketingProduct) {
            return null;
        }
        MarketingProductDto dto = new MarketingProductDto();
        BeanUtils.copyProperties(marketingProduct, dto);
        dto.setId(marketingProduct.getMarketingProductId().getId());
        return dto;
    }
}
