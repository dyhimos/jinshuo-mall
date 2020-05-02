package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.idgen.IdGen;
import com.jinshuo.mall.domain.item.unit.model.GoodsUnit;
import com.jinshuo.mall.domain.item.unit.model.GoodsUnitId;
import com.jinshuo.mall.service.item.application.qry.UnitQry;
import com.jinshuo.mall.service.item.mybatis.mapper.GoodUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

/**
 * Created by 19458 on 2019/7/9.
 */
@Repository
public class GoodUnitRepo  {

    @Autowired(required = false)
    private IdGen idGen;

    @Autowired(required = false)
    private GoodUnitMapper unitMapper;


    public GoodsUnitId nextId() {
        //Long l = idGen.generateId();
        Long l = new Random().nextLong();
        return new GoodsUnitId(l);
    }

    public int delete(Long id) {
        return unitMapper.delete(id);
    }


    public void save(GoodsUnit unit) {
        unitMapper.create(unit);
    }

    public void update(GoodsUnit unit) {
        unitMapper.update(unit);
    }


    public GoodsUnit findById(GoodsUnitId goodsUnitId) {
        return unitMapper.findById(goodsUnitId.getId());
    }


    public PageInfo<GoodsUnit> findAll(UnitQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<GoodsUnit> list = unitMapper.findAll();
        PageInfo<GoodsUnit> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<GoodsUnit> findAll() {
        return unitMapper.findAll();
    }
}
