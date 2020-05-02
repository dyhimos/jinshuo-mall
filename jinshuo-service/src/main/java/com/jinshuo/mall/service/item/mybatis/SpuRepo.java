package com.jinshuo.mall.service.item.mybatis;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.idgen.IdGen;
import com.jinshuo.mall.domain.item.spu.Spu;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.service.item.application.dto.FrontSpuDto;
import com.jinshuo.mall.service.item.application.dto.TopicProductDto;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.application.qry.TopicProductPageQry;
import com.jinshuo.mall.service.item.application.qry.TopicProductQry;
import com.jinshuo.mall.service.item.mybatis.mapper.SpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dyh
 */
@Repository
public class SpuRepo {

    @Autowired(required = false)
    private IdGen idGen;

    @Autowired(required = false)
    private SpuMapper spuMapper;

    public SpuId nextId() {
        return new SpuId(idGen.generateId());
    }

    public void save(Spu spu) {
        spuMapper.create(spu);
    }

    public void update(Spu spu) {
        spuMapper.update(spu);
    }

    /**
     * 前端查询产品列表
     *
     * @param qry
     * @return
     */
    public List<FrontSpuDto> frontFindAll(SpuQry qry) {
        return spuMapper.frontQuerySpuSql(qry);
    }

    /**
     * 后端查询产品列表
     *
     * @param qry
     * @return
     */
    public PageInfo<Spu> findAll(SpuQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Spu> list = spuMapper.testFindAll(qry);
        PageInfo<Spu> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public Spu findBySpuId(Long spuId) {
        return spuMapper.queryBySpuId(spuId);
    }

    public int deleteBySpuId(Long spuId) {
        return spuMapper.deleteById(spuId);
    }

    /**
     * 后端查询产品列表
     *
     * @param qry
     * @return
     */
    public PageInfo<Spu> findAllNew(SpuQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Spu> list = spuMapper.querySpuSqlNew(qry);
        PageInfo<Spu> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询优品
     *
     * @param qry
     * @return
     */
    public List<FrontSpuDto> findExcellent(SpuQry qry) {
        return spuMapper.queryExcellent(qry);
    }

    /**
     * 查询分销产品
     *
     * @param qry
     * @return
     */
    public List<FrontSpuDto> findDis(SpuQry qry) {
        return spuMapper.queryDis(qry);
    }

    /**
     * 活动主题查询产品
     *
     * @param qry
     * @return
     */
    public List<TopicProductDto> findTopicProduct(TopicProductQry qry) {
        List<TopicProductDto> list = spuMapper.queryTopicProduct(qry);
        return list;
    }

    /**
     * 活动尚未被主题活动选中的产品
     *
     * @param qry
     * @return
     */
    public List<TopicProductDto> getNotYetTopicProduct(TopicProductPageQry qry) {
        List<TopicProductDto> list = spuMapper.queryNotYetProductByFront(qry);
        return list;
    }

    /**
     * 根据主题查询已选中的产品（前台）
     *
     * @param qry
     * @return
     */
    public List<TopicProductDto> findTopicProductByFront(TopicProductQry qry) {
        List<TopicProductDto> list = spuMapper.queryTopicProductByFront(qry);
        return list;
    }

    public int updateStock(Spu spu) {
        return spuMapper.updateStock(spu);
    }

    public int upProduct(Long spuId, Integer goodsStatus) {
        return spuMapper.upProduct(spuId, goodsStatus);
    }

    public int updateSort(Long spuId, Integer sort) {
        return spuMapper.updateSort(spuId, sort);
    }

    public int updateDis(Long id, Integer isDis) {
        return spuMapper.updateDis(id, isDis);
    }

    public List<Long> querySpuWithParam(SpuQry qry) {
        return spuMapper.querySpuWithParam(qry);
    }

    public int getSoldOutCount(Long shopId) {
        return spuMapper.querySoldOutCount(shopId);
    }
}
