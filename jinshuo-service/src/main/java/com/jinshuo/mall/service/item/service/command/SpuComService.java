package com.jinshuo.mall.service.item.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.item.brand.BrandId;
import com.jinshuo.mall.domain.item.group.GroupId;
import com.jinshuo.mall.domain.item.shop.ShopId;
import com.jinshuo.mall.domain.item.sku.Sku;
import com.jinshuo.mall.domain.item.spu.Spu;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.service.item.application.cmd.SkuCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuStockUpdateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.SpuRepo;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname SpuComService
 * @Description TODO
 * @Date 2019/7/8 15:37
 * @Created by dyh
 */
@Slf4j
@Service
public class SpuComService {

    @Autowired
    private SpuRepo spuRepo;

    @Autowired
    private SkuComService skuComService;

    @Autowired
    private SpuTagComService spuTagComService;

    @Autowired
    private SpuQueryService spuQueryService;

    @Autowired
    private AlbumComService albumComService;

    @Autowired
    private SpuOtherComService spuOtherComService;

    @Autowired
    private SpuParameterComService spuParameterComService;

    /**
     * 新增商品
     *
     * @param cmd
     * @return spu
     */
    @Transactional
    public Spu save(SpuUpdateCmd cmd) {
        log.info(" -- 新增商品，输入参数：" + JSONObject.toJSONString(cmd));
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        SpuId spuId = new SpuId(CommonSelfIdGenerator.generateId());
        if (0 == cmd.getSingleSku() || null == cmd.getSkus() || cmd.getSkus().size() < 1 || StringUtils.isBlank(cmd.getSkus().get(0).getName())) {
            cmd.setSingleSku(0);
            cmd.setSkus(changeSku(cmd));
        }
        String categoryId = cmd.getCategoryId().stream().collect(Collectors.joining(","));
        //String areaNames = cmd.getAreaNames().stream().collect(Collectors.joining(","));
        Spu spu = Spu.builder()
                .spuId(spuId)
                .name(cmd.getName())
                .categoryId(categoryId)
                .shopId(new ShopId(cmd.getShopId()))
                .typeId(cmd.getTypeId())
                .brandId(new BrandId(cmd.getBrandId()))
                .groudId(new GroupId(cmd.getBrandId()))
                .tag(cmd.getTag())
                .sketch(cmd.getSketch())
                .pictureUrl(cmd.getPictureUrl())
                .spuCode(cmd.getSpuCode())
                .unit(cmd.getUnit())
                .price(cmd.getPrice())
                .marketPrice(cmd.getMarketPrice())
                .costPrice(cmd.getCostPrice())
                .stock(cmd.getStock() - cmd.getVirtualSales())
                .warningStock(cmd.getWarningStock())
                .virtualSales(cmd.getVirtualSales())
                .isIntegral(cmd.getIsIntegral())
                .integral(cmd.getIntegral())
                .goodsStatus(cmd.getGoodsStatus())
                .isHot(cmd.getIsHot())
                .sort(cmd.getSort())
                .supplierId(cmd.getSupplierId())
                .isSendcode(cmd.getIsSendcode())
                .reserveAddress(cmd.getReserveAddress())
                .poster(cmd.getPoster())
                .featureId(cmd.getFeatureId())
                .merchantId(UserIdUtils.getMerchantId())
                .services(cmd.getServices())
                .singleSku(cmd.getSingleSku())
                //.areaNames(areaNames)
                .build();
//        spu.checkCategory();
        spu.preInsert();
        spuRepo.save(spu);
        skuComService.create(cmd.getSkus(), spuId);
        if (null != cmd.getTags()) {
            spuTagComService.create(cmd.getTags(), spuId.getId());
        }
        //保存产品专辑
        albumComService.add(spu.getSpuId().getId(), cmd.getUrls());
        spuParameterComService.save(spu.getSpuId().getId(),cmd.getParams());
        return spu;
    }

    /**
     * 修改商品
     *
     * @param cmd
     * @return spu
     */
    @Transactional
    public Spu update(SpuUpdateCmd cmd) {
        log.info(" -- 修改商品，输入参数：" + JSONObject.toJSONString(cmd));
        if (null == cmd.getId()) {
            throw new IcBizException(IcReturnCode.IC201009.getMsg());
        }
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        if (0 == cmd.getSingleSku() || null == cmd.getSkus() || cmd.getSkus().size() < 1 || StringUtils.isBlank(cmd.getSkus().get(0).getName())) {
            cmd.setSingleSku(0);
            cmd.setSkus(changeSku(cmd));
        }
        String categoryId = cmd.getCategoryId().stream().collect(Collectors.joining(","));
        //String areaNames = cmd.getAreaNames().stream().collect(Collectors.joining(","));
        Spu spu = Spu.builder()
                .spuId(new SpuId(cmd.getId()))
                .name(cmd.getName())
                .categoryId(categoryId)
                .shopId(new ShopId(cmd.getShopId()))
                .typeId(cmd.getTypeId())
                .brandId(new BrandId(cmd.getBrandId()))
                .groudId(new GroupId(cmd.getBrandId()))
                .tag(cmd.getTag())
                .sketch(cmd.getSketch())
                .pictureUrl(cmd.getPictureUrl())
                .spuCode(cmd.getSpuCode())
                .unit(cmd.getUnit())
                .price(cmd.getPrice())
                .marketPrice(cmd.getMarketPrice())
                .costPrice(cmd.getCostPrice())
                .stock(cmd.getStock() - cmd.getVirtualSales())
                .warningStock(cmd.getWarningStock())
                .virtualSales(cmd.getVirtualSales())
                .isIntegral(cmd.getIsIntegral())
                .integral(cmd.getIntegral())
                .goodsStatus(cmd.getGoodsStatus())
                .sort(cmd.getSort())
                .isHot(cmd.getIsHot())
                .supplierId(cmd.getSupplierId())
                .isSendcode(cmd.getIsSendcode())
                .reserveAddress(cmd.getReserveAddress())
                .poster(cmd.getPoster())
                .featureId(cmd.getFeatureId())
                .services(cmd.getServices())
                .singleSku(cmd.getSingleSku())
                //.areaNames(areaNames)
                .build();
        spu.update();
        spuRepo.update(spu);
        skuComService.create(cmd.getSkus(), new SpuId(cmd.getId()));
        spuTagComService.create(cmd.getTags(), cmd.getId());
        if (null != cmd.getUrls()) {
            albumComService.add(spu.getSpuId().getId(), cmd.getUrls());
        }
        spuParameterComService.save(spu.getSpuId().getId(),cmd.getParams());
        return spu;
    }


    /**
     * 删除商品
     *
     * @param spuId
     * @return int 删除数量
     */
    public int deleteBySpuId(Long spuId) {
        return spuRepo.deleteBySpuId(spuId);
    }

    /**
     * 减扣商品库存
     *
     * @param cmd
     */
    @Transactional
    public void cutStock(SpuStockUpdateCmd cmd) {
        log.info(" -- 减扣商品库存,输入参数：" + JSONObject.toJSONString(cmd));
        Sku sku = skuComService.cutSkuStock(cmd);
        spuOtherComService.checkSpuOther(sku.getSpuId().getId());
        Spu spu = spuQueryService.getBySpuId(sku.getSpuId().getId());
        if (null == spu) {
            throw new IcBizException(IcReturnCode.IC201007.getCode(), IcReturnCode.IC201007.getMsg());
        }
        spu.cutStock(cmd.getStock());
        int count = spuRepo.updateStock(spu);
        log.info(" -- 减扣商品库存,修改结果：" + count);
    }

    /**
     * 上下架产品
     *
     * @param spuId
     * @return int 成功数量
     */
    public int upProduct(Long spuId, Integer goodsStatus) {
        log.info(" -- 上下架产品");
        Spu spu = spuQueryService.getBySpuId(spuId);
        return spuRepo.upProduct(spuId, goodsStatus);
    }

    /**
     * 修改商品排序
     *
     * @param spuId
     * @return int 修改成功数量
     */
    public int updateSort(Long spuId, Integer sort) {
        return spuRepo.updateSort(spuId, sort);
    }


    /**
     * 校验商品库存
     *
     * @param cmd
     */
    @Transactional
    public void checkStock(SpuStockUpdateCmd cmd) {
        log.info(" -- 校验商品库存成功,输入参数：" + JSONObject.toJSONString(cmd));
        Sku sku = skuComService.checkSkuStock(cmd);
        Spu spu = spuQueryService.getBySpuId(sku.getSpuId().getId());
        if (null == spu) {
            throw new IcBizException(IcReturnCode.IC201007.getCode(), IcReturnCode.IC201007.getMsg());
        }
        spuOtherComService.checkSpuOther(sku.getSpuId().getId());
        spu.checkStock(cmd.getStock());
        log.info(" -- 校验商品库存成功：");
    }

    public int updateDis(Long id, Integer isDis) {
        return spuRepo.updateDis(id, isDis);
    }

    /**
     * @return
     */
    public List<SkuCreateCmd> changeSku(SpuUpdateCmd spuCreateCmd) {
        List<SkuCreateCmd> skus = new ArrayList<>();
        SkuCreateCmd sku = SkuCreateCmd.builder()
                .name(spuCreateCmd.getName())
                .skuCode(spuCreateCmd.getSpuCode())
                .pictureUrl(spuCreateCmd.getPictureUrl())
                .marketPrice(spuCreateCmd.getMarketPrice())
                .price(spuCreateCmd.getPrice())
                .costPrice(spuCreateCmd.getCostPrice())
                .stock(spuCreateCmd.getStock())
                .build();
        skus.add(sku);
        return skus;
    }
}
