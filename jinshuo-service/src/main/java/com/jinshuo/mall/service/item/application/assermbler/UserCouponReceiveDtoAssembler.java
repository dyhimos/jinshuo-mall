package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.item.couponreceive.CouponReceive;
import com.jinshuo.mall.service.item.application.dto.UserCouponReceiveDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/19.
 */
public class UserCouponReceiveDtoAssembler {
    /**
     * @param couponReceive
     * @return UserCouponReceiveDto
     */
    public static UserCouponReceiveDto assembleDto(CouponReceive couponReceive) {
        if (couponReceive == null) {
            return null;
        }
        UserCouponReceiveDto dto = new UserCouponReceiveDto();
        BeanUtils.copyProperties(couponReceive, dto);
        return dto;
    }


    /**
     * 转化为前端DTO
     *
     * @param couponReceive
     * @return skuDto
     */
    public static UserCouponReceiveDto assembleUserSkuDto(CouponReceive couponReceive, Coupon coupon) {
        if (null == couponReceive || null == coupon) {
            return null;
        }
        UserCouponReceiveDto dto = new UserCouponReceiveDto();
        BeanUtils.copyProperties(couponReceive, dto);
        BeanUtils.copyProperties(coupon, dto);
        dto.setId(couponReceive.getCouponReceiveId().getId().toString());
        return dto;
    }
}
