package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.parameter.CategoryParameter;
import com.jinshuo.mall.service.item.mybatis.mapper.CategoryParameterMapper;
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
public class CategoryParameterRepo {

    @Autowired(required = false)
    private CategoryParameterMapper categoryParameterMapper;


    public void save(CategoryParameter categoryParameter) {
        categoryParameterMapper.create(categoryParameter);
    }

    public List<CategoryParameter> queryById(Long categoryId) {
        return categoryParameterMapper.findByCategoryId(categoryId);
    }

    public int delete(Long id) {
        return categoryParameterMapper.delete(id);
    }
}
