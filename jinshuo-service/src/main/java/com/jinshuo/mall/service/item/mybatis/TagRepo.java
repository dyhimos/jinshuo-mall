package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.tag.Tag;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.mybatis.mapper.TagMapper;
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
public class TagRepo {

    @Autowired(required = false)
    private TagMapper tagMapper;

    public void save(Tag tag) {
        tagMapper.create(tag);
    }

    public int update(Tag tag) {
        return tagMapper.update(tag);
    }

    /*public PageInfo<Tag> findAll(SpuQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Spu> list = spuMapper.findAll(qry);
        PageInfo<Spu> pageInfo = new PageInfo(list);
        return pageInfo;
    }*/

    public Tag queryById(Long tagId) {
        return tagMapper.queryById(tagId);
    }


    public List<Tag> findAll() {
        List<Tag> list = tagMapper.findAll();
        return list;
    }

    public int delete(Long tagId) {
        return tagMapper.delete(tagId);
    }


    public PageInfo<Tag> getByPage(TagQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Tag> list = tagMapper.findAll();
        PageInfo<Tag> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
