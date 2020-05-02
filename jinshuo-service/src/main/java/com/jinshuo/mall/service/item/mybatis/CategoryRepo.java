package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.idgen.IdGen;
import com.jinshuo.mall.domain.item.category.model.Category;
import com.jinshuo.mall.domain.item.category.model.CategoryId;
import com.jinshuo.mall.service.item.application.qry.CategoryQry;
import com.jinshuo.mall.service.item.mybatis.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;


/**
 * Created by dyh
 * Time 2019/7/13 下午3:08
 */
@Repository
public class CategoryRepo {

    @Autowired(required = false)
    private IdGen idGen;

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    public CategoryId nextId() {
        //Long l = idGen.generateId();
        Long l = new Random().nextLong();
        return new CategoryId(l);
    }

    public void save(Category category) {
        categoryMapper.create(category);
    }

    public void update(Category category) {
        categoryMapper.update(category);
    }

    public void delete(CategoryId id) {
        categoryMapper.delete(id.getId());
    }

    public Category findByCategoryId(Long id) {
        return categoryMapper.findById(id);
    }

    public PageInfo<Category> getByPage(CategoryQry qry) {
        //分页查询
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Category> list = categoryMapper.findAll();
        PageInfo<Category> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<Category> findByExample(Category category) {
        return categoryMapper.findByExample(category);
    }

    public List<Category> findAll(CategoryQry qry) {
        return categoryMapper.findAll();
    }

}
