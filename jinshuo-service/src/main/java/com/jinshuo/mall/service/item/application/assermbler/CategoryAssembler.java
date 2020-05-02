package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.category.model.Category;
import com.jinshuo.mall.service.item.application.dto.CategoryDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/15.
 */
public class CategoryAssembler {

    /**
     * @param category
     * @return
     */
    public static CategoryDto assembleCategoryDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        categoryDto.setId(category.getCategoryId().getId().toString());
        if (null != category.getPid()) {
            categoryDto.setPid(category.getPid().getId().toString());
        }
        categoryDto.setLevel(category.getCategoryLevel().getLevel());
        return categoryDto;
    }
}
