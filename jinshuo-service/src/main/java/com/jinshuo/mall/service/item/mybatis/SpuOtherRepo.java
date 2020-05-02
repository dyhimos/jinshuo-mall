package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.spuOther.SpuOther;
import com.jinshuo.mall.service.item.mybatis.mapper.SpuOtherMapper;
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
public class SpuOtherRepo {

    @Autowired(required = false)
    private SpuOtherMapper spuOtherMapper;

    public void save(SpuOther tag) {
        spuOtherMapper.create(tag);
    }

    public void update(SpuOther tag) {
        spuOtherMapper.update(tag);
    }

    /*public PageInfo<Tag> findAll(SpuQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Spu> list = spuMapper.findAll(qry);
        PageInfo<Spu> pageInfo = new PageInfo(list);
        return pageInfo;
    }*/

    public SpuOther queryById(Long tagId) {
        return spuOtherMapper.queryById(tagId);
    }

    public SpuOther queryBySpuId(Long spuId) {
        return spuOtherMapper.queryBySpuId(spuId);
    }

    public List<SpuOther> findAll() {
        List<SpuOther> list = spuOtherMapper.findAll();
        return list;
    }
}
