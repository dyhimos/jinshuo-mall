package com.jinshuo.mall.service.user.service.query;

import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.mall.domain.user.model.shop.Shop;
import com.jinshuo.mall.service.user.application.assermbler.ShopAssembler;
import com.jinshuo.mall.service.user.application.dto.ShopDto;
import com.jinshuo.mall.service.user.mybatis.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class ShopQueryService {


    @Autowired
    private ShopRepo shopRepo;


    /**
     * 查询店铺集合
     *
     * @param
     * @return Address
     */
    public List<ShopDto> getList(Long merchantId) {
        Shop shop = new Shop();
        shop.setMerchantId(merchantId);
        List<Shop> list = shopRepo.findAll(shop);
        List<ShopDto> dtos = list
                .stream()
                .map(shop1 -> ShopAssembler.assembleShopDto(shop1))
                .collect(Collectors.toList());
        return dtos;
    }


    /**
     * 查询店铺信息
     * @param id
     * @return
     */
    public ShopDto queryDetail(Long id) {
        Shop shop = shopRepo.findById(id);
        //判断店铺是否存在
        if(shop == null){
            throw new UcBizException(UcReturnCode.UC200042.getMsg(),UcReturnCode.UC200042.getCode());
        }
        return ShopAssembler.assembleShopDto(shop);
    }

}
