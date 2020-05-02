package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import com.jinshuo.mall.service.item.application.dto.CouponItemDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class CouponItemAssembler {
    /**
     * @param couponItem
     * @return
     */
    public static CouponItemDto assembleCouponDto(CouponItem couponItem) {
        if (couponItem == null) {
            return null;
        }
        CouponItemDto dto = new CouponItemDto();
        BeanUtils.copyProperties(couponItem, dto);
        dto.setId(couponItem.getCouponId().getId().toString());
        dto.setTargetId(couponItem.getTargetId().toString());
        return dto;
    }
}
