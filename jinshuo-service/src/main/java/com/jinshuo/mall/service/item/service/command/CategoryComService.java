package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.category.model.Category;
import com.jinshuo.mall.domain.item.category.model.CategoryId;
import com.jinshuo.mall.service.item.application.cmd.CategoryCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.CategoryUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by 19458 on 2019/7/3.
 */
@Service
public class CategoryComService {

    @Autowired
    private CategoryRepo repo;

    /**
     * 新增商品类目
     *
     * @param dto
     */
    public Category create(CategoryCreateCmd dto) {
        Category pCategory = repo.findByCategoryId(dto.getPid());
        Category category = new Category(
                new CategoryId(CommonSelfIdGenerator.generateId()),
                pCategory,
                dto.getLeaf(),
                dto.getName(),
                dto.getCateType(),
                dto.getOrderSeq(),
                dto.getPictureUrl(),
                dto.getBackDategories(),
                dto.getNeedAudit(),dto.getShopId(),dto.getIsShow());
        repo.save(category);
        return category;
    }

    Optional<Category> findSpuBasicById(CategoryId categoryId) {
        return Optional.ofNullable(repo.findByCategoryId(categoryId.getId()));
    }

    /**
     * 修改商品类目
     *
     * @param cmd
     */
    public void update(CategoryUpdateCmd cmd) {
        Category pCategory = repo.findByCategoryId(cmd.getPid());
        Category category2 = new Category(
                new CategoryId(cmd.getId()),
                pCategory,
                cmd.getLeaf(),
                cmd.getName(),
                cmd.getCateType(),
                cmd.getOrderSeq(),
                cmd.getPictureUrl(),
                cmd.getBackDategories(),
                cmd.getNeedAudit(),
                cmd.getShopId(),cmd.getIsShow());
        CategoryId categoryId = new CategoryId(cmd.getId());
        this.findSpuBasicById(categoryId)
                .map(category -> category.updateCategory(category2))
                .ifPresent(category -> repo.update(category));
    }

    public void delete(Long id) {
        repo.delete(new CategoryId(id));
    }

}
