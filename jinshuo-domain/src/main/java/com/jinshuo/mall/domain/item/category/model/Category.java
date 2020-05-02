package com.jinshuo.mall.domain.item.category.model;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.UrlUtil;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;


/**
 * Created by dyh
 * Time 2019/7/13 上午10:13
 */
@Data
@Builder
@Getter
@Accessors(chain = true)
@ToString(callSuper = true)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Category extends IdentifiedEntity {

    @NotNull(message = "ID")
    private CategoryId categoryId;

    @NotNull(message = "父ID")
    private CategoryId pid;

    @NotNull(message = "是否叶子节点 是否叶子节点 1：是0：不是") //
    private Integer leaf;

    @NotNull(message = "层级")
    private CategoryLevel categoryLevel;

    @NotNull(message = "父分类不能为空")
    private String name;

    @NotNull(message = "类目类型 1：前台 2：后台 ")
    private Integer cateType;

    @NotNull(message = "排序序列")
    private Integer orderSeq;

    @NotNull(message = "类目图片")
    private String pictureUrl;

    @NotNull(message = "后台类目id集合")
    private String backCategories;

    @NotNull(message = "是否需要审核  0：是 1：不是")
    private Integer needAudit;

    private Long shopId;

    @NotNull(message = "是否展示 0:是 1:是展示")
    private Integer isShow;

    /**
     * 分类创建入口
     *
     * @param id
     * @param pCategory
     * @param name
     * @param leaf
     */
    public Category(CategoryId id, Category pCategory, Integer leaf, String name, Integer cateType, Integer orderSeq, String pictureUrl, String backCategories, Integer needAudit, Long shopId, Integer isShow) {
        this.categoryId = id;
        this.leaf = leaf;
        this.name = name;
        this.cateType = cateType;
        this.orderSeq = orderSeq;
        this.pictureUrl = pictureUrl;
        this.backCategories = backCategories;
        this.needAudit = needAudit;
        this.isShow = isShow;
        if (null==shopId) {
            shopId = 10088l;
        }
        this.shopId = shopId;
        if (null == pCategory) {
            this.categoryLevel = new CategoryLevel(1);
            this.pid = new CategoryId();
        } else {
            this.categoryLevel = pCategory.getCategoryLevel().nextLevel();
            this.pid = pCategory.getCategoryId();
        }
        preInsert();
        if (null == this.version) {
            this.version = 1;
        } else {
            this.version += 1;
        }
    }

    public Category updateCategory(Category category) {
        Category category1 = this;
        BeanUtils.copyProperties(category, category1);
        return this;
    }

    public Category findById(CategoryId categoryId) {
        return null;
    }

    public void setUrl(String pictureUrl) {
        this.pictureUrl = UrlUtil.getUrl(pictureUrl);
    }

}
