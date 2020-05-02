package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.unit.model.GoodsUnit;
import com.jinshuo.mall.domain.item.unit.model.GoodsUnitId;
import com.jinshuo.mall.service.item.application.assermbler.UnitAssembler;
import com.jinshuo.mall.service.item.application.dto.GoodUnitDto;
import com.jinshuo.mall.service.item.application.qry.UnitQry;
import com.jinshuo.mall.service.item.mybatis.GoodUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/11.
 */
@Service
public class GoodUnitQueryService {

    @Autowired
    private GoodUnitRepo repo;


    public GoodsUnit findUnitById(GoodsUnitId goodsUnitId) {
        return repo.findById(goodsUnitId);
    }

    public Optional<GoodsUnit> findUnitBaseById(GoodsUnitId goodsUnitId) {
        return Optional.ofNullable(repo.findById(goodsUnitId));
    }

    public PageInfo findUnits(UnitQry qry) {
        PageInfo pageInfo = repo.findAll(qry);
        List<GoodsUnit> goodsUnits = pageInfo.getList();
        List<GoodUnitDto> dtos = goodsUnits.stream().map(goodsUnit -> UnitAssembler.assembleUnitDto(goodsUnit)).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    public List<GoodUnitDto> findUnits() {
        List<GoodsUnit> list = repo.findAll();
        List<GoodUnitDto> dtos = list.stream().map(goodsUnit -> UnitAssembler.assembleUnitDto(goodsUnit)).collect(Collectors.toList());
        return dtos;
    }
}
