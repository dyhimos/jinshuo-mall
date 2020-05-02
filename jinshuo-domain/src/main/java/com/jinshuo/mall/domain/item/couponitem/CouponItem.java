package com.jinshuo.mall.domain.item.couponitem;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.item.coupon.CouponId;
import lombok.*;

/**
 * Created by dongyh on 2019/7/30.
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponItem extends IdentifiedEntity {
    private CouponItemId couponItemId;
    private CouponId couponId;//优惠券编码
    private Long targetId;//目标对象
    private Integer type;//类型
    private Integer isApply;//是否适用类型

    public CouponItem update(Long couponId, Long targetId, Integer type, Integer isApply) {
        this.couponId = new CouponId(couponId);
        this.targetId = targetId;
        this.type = type;
        this.isApply = isApply;
        return this;
    }
}
