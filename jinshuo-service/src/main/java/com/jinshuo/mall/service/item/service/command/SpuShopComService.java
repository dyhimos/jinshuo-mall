package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.mall.domain.item.spushop.SpuShop;
import com.jinshuo.mall.service.item.application.cmd.SpuShopCmd;
import com.jinshuo.mall.service.item.mybatis.SpuShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class SpuShopComService {

    @Autowired
    private SpuShopRepo spuShopRepo;


    /**
     * 店铺新增产品
     *
     * @param cmd
     */
    public SpuShop create(SpuShopCmd cmd) {
        SpuShop spuShop = spuShopRepo.findByShopIdSpuId(cmd.getShopId(), cmd.getSpuId());
        if (null != spuShop) {
            return spuShop;
        }
        spuShop = SpuShop.builder()
                .shopId(cmd.getShopId())
                .spuId(cmd.getSpuId())
                .build();
        spuShop.insert();
        spuShopRepo.save(spuShop);
        return spuShop;
    }

    /**
     * 店铺删除产品
     *
     * @param shopId
     */
    public int delete(Long shopId, Long spuId) {
        SpuShop spuShop = spuShopRepo.findByShopIdSpuId(shopId, spuId);
        if (null == spuShop) {
            return 0;
        }
        return spuShopRepo.delete(spuShop.getSpuShopId().getId());
    }
}
