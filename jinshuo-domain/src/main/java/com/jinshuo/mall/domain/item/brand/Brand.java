package com.jinshuo.mall.domain.item.brand;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.UrlUtil;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by dongyh on 2019/7/22.
 */
@Data
@ToString(callSuper = true)
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends IdentifiedEntity {
    private BrandId brandId;
    private String name;
    private String pictureUrl;
    private Integer itemNum;
    private Integer sort;

    public Brand update(String name, String pictureUrl, Integer sort) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.sort = sort;
        return this;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = UrlUtil.getUrl(pictureUrl);
    }
}
