package com.jinshuo.mall.service.user.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.user.model.collect.Collect;
import com.jinshuo.mall.service.user.application.qry.CollectQry;
import com.jinshuo.mall.service.user.mybatis.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class CollectRepo {

    @Autowired(required = false)
    private CollectMapper collectMapper;

    public int save(Collect collect) {
        return collectMapper.create(collect);
    }

    public int update(Collect collect) {
        return collectMapper.update(collect);
    }

    public Collect queryById(Long collectId) {
        return collectMapper.queryById(collectId);
    }

    public List<Collect> findAll(Long memId) {
        List<Collect> list = collectMapper.findAll(memId);
        return list;
    }

    public int delete(Long memId,Long collectId) {
        return collectMapper.delete(memId,collectId);
    }

    public PageInfo<Collect> getByPage(CollectQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Collect> list = collectMapper.findAll(qry.getMemId());
        PageInfo<Collect> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<Collect> queryByTargetId(Long memId, Long targetId) {
        return collectMapper.queryByTargetId(memId, targetId);
    }
}
