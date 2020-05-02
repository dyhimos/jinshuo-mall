package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.type.Type;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.mybatis.mapper.TypeMapper;
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
public class TypeRepo {

    @Autowired(required = false)
    private TypeMapper typeMapper;

    public void save(Type type) {
        typeMapper.create(type);
    }

    public int update(Type type) {
        return typeMapper.update(type);
    }

    public Type queryById(Long typeId) {
        return typeMapper.queryById(typeId);
    }

    public List<Type> findAll() {
        List<Type> list = typeMapper.findAll();
        return list;
    }

    public int delete(Long typeId) {
        return typeMapper.delete(typeId);
    }

    public PageInfo<Type> getByPage(TagQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Type> list = typeMapper.findAll();
        PageInfo<Type> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
