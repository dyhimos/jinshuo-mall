package com.jinshuo.mall.domain.item.spec;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.item.category.model.CategoryId;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * Created by dongyh on 2019/7/17.
 */
@Data
@Builder
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Spec extends IdentifiedEntity {
    private SpecId specId;
    private Long shopId;
    private String name;
    private CategoryId categoryId;
    private Integer sort;

    public Spec changeName(String name){
        if(Objects.equals(name,this.name)){
            return this;
        }
        this.name = name;
        this.preUpdate();
        return this;
    }

    public Spec changeCategory(CategoryId categoryId){
        if(Objects.equals(categoryId,this.categoryId)){
            return this;
        }
        this.categoryId = categoryId;
        return this;
    }

    public Spec changeName(Integer sort){
        if(Objects.equals(sort,this.sort)){
            return this;
        }
        this.sort = sort;
        return this;
    }

}
