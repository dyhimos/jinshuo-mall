package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.mall.domain.item.category.model.Category;
import com.jinshuo.mall.domain.item.category.model.CategoryId;
import com.jinshuo.mall.domain.item.category.model.CategoryLevel;
import com.jinshuo.mall.service.item.application.assermbler.CategoryAssembler;
import com.jinshuo.mall.service.item.application.dto.CategoryDto;
import com.jinshuo.mall.service.item.application.qry.CategoryQry;
import com.jinshuo.mall.service.item.mybatis.CategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/15.
 */
@Slf4j
@Service
public class CategoryQueryService {

    @Autowired
    private CategoryRepo repo;

    public List<Category> getCategoryList() {
        return null;
    }

    public PageInfo getCategorysByPage(CategoryQry qry) {
        PageInfo pageInfo = repo.getByPage(qry);
        List<Category> categories = pageInfo.getList();
        List<CategoryDto> dtos = categories.stream().map(category -> CategoryAssembler.assembleCategoryDto(category)).collect(Collectors.toList());
        //List<CategoryDto> dtos1 = dtos.stream().map(categoryDto -> categoryDto.setPname(""));
        pageInfo.setList(dtos);
        return pageInfo;
    }

    public Category getById(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }


    /**
     * 查询类目集合，含子类目
     *
     * @param qry
     * @return
     */
    public List<CategoryDto> getCategorys(CategoryQry qry) {
        if (null == qry.getShopId()) {
            qry.setShopId(DefaultShopId.SHOPID);
        }
        //查詢一級
        List<Category> list = repo.findByExample(Category.builder().categoryLevel(new CategoryLevel(1)).shopId(qry.getShopId()).build());
        List<CategoryDto> dtos = list.stream().map(category1 -> CategoryAssembler.assembleCategoryDto(category1)).collect(Collectors.toList());
        dtos.forEach(categoryDto -> {
            queryChildren(categoryDto);
        });
        return dtos;
    }

    /**
     * 添加类目的子类目
     *
     * @param dto
     * @return
     */
    public CategoryDto queryChildren(CategoryDto dto) {
        List<Category> list = repo.findByExample(Category.builder().pid(new CategoryId(Long.parseLong(dto.getId()))).build());
        if (null == list) {
            return dto;
        }
        List<CategoryDto> dtos = list.stream().map(category -> {
            CategoryDto dto1 = CategoryAssembler.assembleCategoryDto(category);
            dto1 = queryChildren(dto1);
            return dto1;
        }).collect(Collectors.toList());
        dto.setChildren(dtos);
        return dto;
    }


    /**
     * 查询首页展示类目
     *
     * @param
     * @return
     */
    public List<CategoryDto> getFirstPageCategorys(CategoryQry qry) {
        log.info(" -- 查询首页展示类目");
        if (null == qry.getShopId()) {
            qry.setShopId(DefaultShopId.SHOPID);
        }
        Category category = Category.builder().categoryLevel(new CategoryLevel(1)).isShow(0)
                .shopId(qry.getShopId()).build();
        List<Category> list = repo.findByExample(category);
        List<CategoryDto> dtos = null;
        dtos = list.stream().map(category1 -> CategoryAssembler.assembleCategoryDto(category1)).collect(Collectors.toList());
        log.info(" -- 查询首页展示类目dtos :" + dtos.toString());
        return dtos;
    }


}
