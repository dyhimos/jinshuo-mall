package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.service.item.application.dto.CouponDto;
import com.jinshuo.mall.service.item.application.dto.UserCouponDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class CouponAssembler {
    /**
     * @param coupon
     * @return
     */
    public static CouponDto assembleCouponDto(Coupon coupon) {
        if (coupon == null) {
            return null;
        }
        CouponDto dto = new CouponDto();
        BeanUtils.copyProperties(coupon, dto);
        dto.setId(coupon.getCouponId().getId().toString());
        return dto;
    }

    /**
     * @param coupon
     * @return
     */
    public static UserCouponDto assembleUserDto(Coupon coupon) {
        if (coupon == null) {
            return null;
        }
        UserCouponDto dto = new UserCouponDto();
        BeanUtils.copyProperties(coupon, dto);
        dto.setId(coupon.getCouponId().getId().toString());
        return dto;
    }
}
