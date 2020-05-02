package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.feature.Feature;
import com.jinshuo.mall.service.item.application.assermbler.FeatureAssembler;
import com.jinshuo.mall.service.item.application.dto.FeatureDto;
import com.jinshuo.mall.service.item.application.qry.FeatureQry;
import com.jinshuo.mall.service.item.application.qry.LatticeQry;
import com.jinshuo.mall.service.item.mybatis.FeatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class FeatureQueryService {

    @Autowired
    private FeatureRepo featureRepo;


    /**
     * 根据id查询产品属性
     *
     * @return
     */
    public Feature getById(Long id) {
        return featureRepo.queryById(id);
    }

    /**
     * 获取产品属性（特征）下拉框
     *
     * @return
     */
    public List<FeatureDto> getAll(LatticeQry qry) {
        List<Feature> list = featureRepo.findAll(qry.getShopId());
        List<FeatureDto> dtos = list.stream()
                .map(feature -> FeatureAssembler.assembleDto(feature)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * 分页产品属性（特征）
     *
     * @return
     */
    public PageInfo getByPage(FeatureQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        Long merchantId = 1000011l;
        List<Feature> list = featureRepo.findAll(merchantId);
        PageInfo pageInfo = new PageInfo(list);
        List<FeatureDto> dtos = list.stream()
                .map(feature -> FeatureAssembler.assembleDto(feature)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }
}
