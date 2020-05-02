package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.spu.Spu;
import com.jinshuo.mall.service.item.application.dto.SkuDto;
import com.jinshuo.mall.service.item.application.dto.SpuDto;
import com.jinshuo.mall.service.item.application.dto.UserSpuDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Classname SpuAssembler
 * @Description Spu模块的组装器，完成domain model对象到dto的转换，组装职责包括：
 * 1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 * 2、将多个model组合成一个dto，一并返回。
 * @Date 2019/6/17 18:27
 * @Created by dyh
 */
@Slf4j
@Component
public class SpuAssembler {

    /**
     * @param spu
     * @return
     */
    public static SpuDto assembleSpuDto(Spu spu) {
        if (spu == null) {
            return null;
        }
        SpuDto spuDto = new SpuDto();
        BeanUtils.copyProperties(spu, spuDto);
        spuDto.setId(spu.getSpuId().getId().toString());
        try {
            if (null != spu.getCategoryId()) {
                List<String> categorys = Arrays.asList(spu.getCategoryId().split(","));
                spuDto.setCategoryId(categorys);
            }
            if (null != spu.getBrandId()) {
                spuDto.setBrandId(spu.getBrandId().getId().toString());
            }
            if (null != spu.getGroudId()) {
                spuDto.setGroudId(spu.getGroudId().getId().toString());
            }
            if (null != spu.getSupplierId()) {
                spuDto.setSupplierId(spu.getSupplierId().toString());
            }
            if (null != spu.getFeatureId()) {
                spuDto.setSupplierId(spu.getFeatureId().toString());
            }
            if (!StringUtils.isEmpty(spu.getAreaNames())) {
                List<String> areaNames = Arrays.asList(spu.getAreaNames().split(","));
                spuDto.setAreaNames(areaNames);
            }
        } catch (Exception e) {
            log.info("--转换失败，不打印错误");
        }
        try {
            spuDto.setTypeId(spu.getTypeId().toString());
        } catch (Exception e) {
            log.error("系統错误,", e);
        }
        return spuDto;
    }

    /**
     * @param spu
     * @return
     */
    public static SpuDto assembleSpuDto(Spu spu, List<SkuDto> skuDtos) {
        if (spu == null) {
            return null;
        }
        SpuDto spuDto = new SpuDto();
        BeanUtils.copyProperties(spu, spuDto);
        spuDto.setId(spu.getSpuId().getId().toString());
        try {
            if (null != spu.getShopId()) {
                spuDto.setShopId(spu.getShopId().getId().toString());
            }
            if (null != spu.getCategoryId()) {
                List<String> categorys = Arrays.asList(spu.getCategoryId().split(","));
                spuDto.setCategoryId(categorys);
            }
            if (null != spu.getBrandId()) {
                spuDto.setBrandId(spu.getBrandId().getId().toString());
            }
            if (null != spu.getGroudId()) {
                spuDto.setGroudId(spu.getGroudId().getId().toString());
            }
            if (null != spu.getSupplierId()) {
                spuDto.setSupplierId(spu.getSupplierId().toString());
            }
            if (null != spu.getFeatureId()) {
                spuDto.setSupplierId(spu.getFeatureId().toString());
            }
            if (!StringUtils.isEmpty(spu.getAreaNames())) {
                List<String> areaNames = Arrays.asList(spu.getAreaNames().split(","));
                spuDto.setAreaNames(areaNames);
            }
        } catch (Exception e) {
            log.info("--转换失败，不打印错误");
            //log.error("系統错误,",e);
        }
        try {
            spuDto.setTypeId(spu.getTypeId().toString());
        } catch (Exception e) {
            log.error("系統错误,", e);
        }
        try {
            if (null != spu.getSupplierId()) {
                spuDto.setSupplierId(spu.getSupplierId().toString());
            }
        } catch (Exception e) {
            log.error("系統错误,", e);
        }
        if (null != skuDtos) {
            spuDto.setSkus(skuDtos);
        }
        return spuDto;
    }

    /**
     * 转化成前端DTO
     *
     * @param spu
     * @return
     */
    public static UserSpuDto assembleUserSpuDto(Spu spu) {
        if (spu == null) {
            return null;
        }
        UserSpuDto userSpuDto = new UserSpuDto();
        BeanUtils.copyProperties(spu, userSpuDto);
        userSpuDto.setId(spu.getSpuId().getId().toString());
        try {
            if (null != spu.getCategoryId()) {
                List<String> categorys = Arrays.asList(spu.getCategoryId().split(","));
                userSpuDto.setCategoryId(categorys);
            }
            if (null != spu.getBrandId()) {
                userSpuDto.setBrandId(spu.getBrandId().getId().toString());
            }
            if (null != spu.getGroudId()) {
                userSpuDto.setGroudId(spu.getGroudId().getId().toString());
            }
            if (null != spu.getSupplierId()) {
                userSpuDto.setSupplierId(spu.getSupplierId().toString());
            }
            if (null != spu.getFeatureId()) {
                userSpuDto.setFeatureId(spu.getFeatureId().toString());
            }
            if (!StringUtils.isEmpty(spu.getAreaNames())) {
                List<String> areaNames = Arrays.asList(spu.getAreaNames().split(","));
                userSpuDto.setAreaNames(areaNames);
            }
        } catch (Exception e) {
            log.info("--转换失败，不打印错误");
            //log.error("系統错误,",e);
        }
        try {
            userSpuDto.setTypeId(spu.getTypeId().toString());
        } catch (Exception e) {
            log.error("系統错误,", e);
        }
        try {
            if (null != spu.getSupplierId()) {
                userSpuDto.setSupplierId(spu.getSupplierId().toString());
            }
        } catch (Exception e) {
            log.error("系統错误,", e);
        }
        return userSpuDto;
    }
}
