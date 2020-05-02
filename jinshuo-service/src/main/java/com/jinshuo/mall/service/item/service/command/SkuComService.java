package com.jinshuo.mall.service.item.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.sku.Sku;
import com.jinshuo.mall.domain.item.sku.SkuId;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.service.item.application.cmd.SkuCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuStockUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.SkuRepo;
import com.jinshuo.mall.service.item.service.query.SkuQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 19458 on 2019/7/18.
 */
@Slf4j
@Service
public class SkuComService {

    @Autowired
    private SkuRepo skuRepo;

    @Autowired
    private SkuOptionComService skuOptionComService;

    @Autowired
    private SkuQueryService skuQueryService;


    /**
     * 保存商品SKU
     *
     * @param skuCmds
     */
    @Transactional
    public void create(List<SkuCreateCmd> skuCmds, SpuId spuId) {
        log.info(" -- 保存商品SKU输入参数，" + skuCmds);
        //skuRepo.queryBySpuId(spuId.getId()).forEach(sku -> skuOptionComService.deleteBySkuId(sku.getId()));
        //skuRepo.delete(spuId.getId());
        skuCmds.forEach(skuCreateCmd -> {
            Sku temp = null;
            if (null != skuCreateCmd.getId()) {
                temp = skuRepo.queryBySkuId(skuCreateCmd.getId());
            }
            if (null != temp) {
                Sku sku = Sku.builder()
                        .skuId(new SkuId(skuCreateCmd.getId()))
                        .costPrice(skuCreateCmd.getCostPrice())
                        .marketPrice(skuCreateCmd.getMarketPrice())
                        .price(skuCreateCmd.getPrice())
                        .pictureUrl(skuCreateCmd.getPictureUrl())
                        .name(skuCreateCmd.getName())
                        .salesQuantity(skuCreateCmd.getSalesQuantity())
                        .spuId(spuId)
                        .stock(skuCreateCmd.getStock())
                        .barCode(skuCreateCmd.getBarCode())
                        .skuCode(skuCreateCmd.getSkuCode())
                        .build();
                skuRepo.update(sku);
                skuOptionComService.create(skuCreateCmd.getOptionIds(), new SkuId(skuCreateCmd.getId()));
            } else {
                SkuId skuId = new SkuId(CommonSelfIdGenerator.generateId());
                Sku sku = Sku.builder()
                        .skuId(skuId)
                        .costPrice(skuCreateCmd.getCostPrice())
                        .marketPrice(skuCreateCmd.getMarketPrice())
                        .price(skuCreateCmd.getPrice())
                        .pictureUrl(skuCreateCmd.getPictureUrl())
                        .name(skuCreateCmd.getName())
                        .salesQuantity(skuCreateCmd.getSalesQuantity())
                        .spuId(spuId)
                        .stock(skuCreateCmd.getStock())
                        .barCode(skuCreateCmd.getBarCode())
                        .skuCode(skuCreateCmd.getSkuCode())
                        .build();
                sku.preInsert();
                skuRepo.save(sku);
                skuOptionComService.create(skuCreateCmd.getOptionIds(), skuId);
            }
        });
    }

    /**
     * 减扣库存
     *
     * @param cmd
     * @return Sku
     */
    @Transactional
    public Sku cutSkuStock(SpuStockUpdateCmd cmd) {
        log.info(" -- 减扣sku商品库存,输入参数：" + JSONObject.toJSONString(cmd));
        Sku sku = skuQueryService.getSkusBySkuId(cmd.getSkuId());
        if (null == sku) {
            throw new IcBizException(IcReturnCode.IC201007.getCode(), IcReturnCode.IC201007.getMsg());
        }
        sku.cutStock(cmd.getStock());
        int count = skuRepo.updateStock(sku);
        log.info(" -- 减扣sku商品库存,修改结果：" + count);
        return sku;
    }


    /**
     * 减扣库存
     *
     * @param cmd
     * @return Sku
     */
    @Transactional
    public Sku checkSkuStock(SpuStockUpdateCmd cmd) {
        Sku sku = skuQueryService.getSkusBySkuId(cmd.getSkuId());
        if (null == sku) {
            throw new IcBizException(IcReturnCode.IC201007.getCode(), IcReturnCode.IC201007.getMsg());
        }
        sku.checkStock(cmd.getStock());
        return sku;
    }

    public void deleteSku(Long id){
        skuRepo.deleteById(id);
    }
}
