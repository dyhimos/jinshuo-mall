package com.jinshuo.mall.service.item.service.query;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.utils.MathUtil;
import com.jinshuo.mall.domain.item.sku.Sku;
import com.jinshuo.mall.service.item.application.assermbler.SkuAssembler;
import com.jinshuo.mall.service.item.application.cmd.TargetCmd;
import com.jinshuo.mall.service.item.application.dto.SkuDto;
import com.jinshuo.mall.service.item.application.dto.UserSkuDto;
import com.jinshuo.mall.service.item.mybatis.SkuRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Slf4j
@Service
public class SkuQueryService {

    @Autowired
    private SkuRepo repo;

    @Autowired
    private SkuOptionQueryService skuOptionQueryService;



    public List<Sku> getSkusBySpuId(Long spuId) {
        return repo.queryBySpuId(spuId);
    }

    public Sku getSkusBySkuId(Long skuId) {
        return repo.queryBySkuId(skuId);
    }

    public List<SkuDto> getSkuDtosBySpuId(Long spuId) {
        List<Sku> list = getSkusBySpuId(spuId);
        List<SkuDto> skuDtos = list.stream().map(sku -> SkuAssembler.assembleSkuDto(sku)).collect(Collectors.toList());
        skuDtos.forEach(skuDto -> skuDto.setSpecOptionDtos(skuOptionQueryService.getBySkuId(Long.parseLong(skuDto.getId()))));
        return skuDtos;
    }

    public SkuDto getSkuDtoBySkuId(Long skuId) {
        Sku sku = getSkusBySkuId(skuId);
        if (null == sku) {
            return null;
        }
        SkuDto skuDto = SkuAssembler.assembleSkuDto(sku);
        skuDto.setSpecOptionDtos(skuOptionQueryService.getBySkuId(Long.parseLong(skuDto.getId())));
        return skuDto;
    }

    /**
     * 根据spuId查询UserSkuDto
     *
     * @param spuId
     * @return List<UserSkuDto>
     */
    public List<UserSkuDto> getUserSkuDtosBySpuId(Long spuId) {
        List<Sku> list = getSkusBySpuId(spuId);
        List<UserSkuDto> skuDtos = list.stream().map(sku -> SkuAssembler.assembleUserSkuDto(sku)).collect(Collectors.toList());
        skuDtos.forEach(skuDto -> skuDto.setSpecOptionDtos(skuOptionQueryService.getBySkuId(Long.parseLong(skuDto.getId()))));
        return skuDtos;
    }


    /**
     * 减扣商品库存
     *
     * @param list
     * @return amount
     */
    public BigDecimal getAmountBySkuId(List<TargetCmd> list) {
        log.info(" -- 开始计算订单金额，输入参数" + JSONObject.toJSONString(list));
        BigDecimal amount = new BigDecimal(0);
        for(TargetCmd targetCmd:list){
            Sku sku = repo.queryBySkuId(targetCmd.getTargetId());
            if(null==sku){
                throw new IcBizException(IcReturnCode.IC201007.getCode(), IcReturnCode.IC201007.getMsg());
            }
            amount = MathUtil.add(amount,MathUtil.multiply(sku.getPrice(),new BigDecimal(targetCmd.getCount()),2),2);
        }
        log.info(" -- 开始计算订单金额，输出参数" + amount);
        return amount;
    }
}
