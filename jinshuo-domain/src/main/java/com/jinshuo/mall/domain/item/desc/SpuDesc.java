package com.jinshuo.mall.domain.item.desc;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by dongyh on 2019/7/18.
 */
@Data
@Builder
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SpuDesc extends IdentifiedEntity {

    /**
     * spuDescId
     */
    private SpuDescId spuDescId;

    /**
     * spuId
     */
    private SpuId spuId;

    /**
     * pc端详情
     */
    private String pcDesc;

    /**
     * 客户端详情
     */
    private String mobileDesc;

    /**
     * 预定须知
     */
    private String bookingNotes;

    public SpuDesc update(String pcDesc, String mobileDesc, String bookingNotes) {
        this.pcDesc = pcDesc;
        this.mobileDesc = mobileDesc;
        this.bookingNotes = bookingNotes;
        return this;
    }
}
