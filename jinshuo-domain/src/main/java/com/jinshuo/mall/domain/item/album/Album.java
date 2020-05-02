package com.jinshuo.mall.domain.item.album;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.item.sku.SkuId;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.sun.jndi.toolkit.url.UrlUtil;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by dongyh on 2019/7/18.
 */
@Data
@ToString(callSuper = true)
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album extends IdentifiedEntity {
    private AlbumId albumId;
    private SpuId spuId;
    private SkuId skuId;
    private String url;
    private Integer sort;
    private Integer type;

    public Album update(String url, Integer sort, Integer type) {
        this.url = url;
        this.sort = sort;
        this.type = type;
        this.preUpdate();
        if (null != this.version) {
            this.version += 1;
        }
        return this;
    }

    public void setUrl(String pictureUrl) {
        this.url = pictureUrl;
    }
}
