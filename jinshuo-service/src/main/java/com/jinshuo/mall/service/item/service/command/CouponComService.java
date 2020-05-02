package com.jinshuo.mall.service.item.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.item.coupon.CouponId;
import com.jinshuo.mall.service.item.application.cmd.CouponCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.CouponUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.CouponRepo;
import com.jinshuo.mall.service.item.service.query.CouponQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class CouponComService {

    @Autowired
    private CouponRepo couponRepo;

    @Autowired
    private CouponItemComService couponItemComService;

    @Autowired
    private CouponQueryService couponQueryService;

    /**
     * 新增优惠券
     *
     * @param cmd
     */
    public void create(CouponCreateCmd cmd) {
        log.info(" -- 新增优惠券,输入参数：" + JSONObject.toJSONString(cmd));
        if (null == cmd.getShopId()) {
            cmd.setShopId(10088l);
        }
        if (null == cmd.getGainMethod()) {
            cmd.setGainMethod(1);
        }
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(cmd, coupon);
        coupon.insert();
        couponRepo.save(coupon);
        couponItemComService.create(cmd.getItems(), coupon);
    }

    /**
     * 修改优惠券
     *
     * @param cmd
     */
    public void update(CouponUpdateCmd cmd) {
        log.info(" -- 修改优惠券,输入参数：" + JSONObject.toJSONString(cmd));
        if (null == cmd.getShopId()) {
            cmd.setShopId(10088l);
        }
        if (null == cmd.getGainMethod()) {
            cmd.setGainMethod(1);
        }
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(cmd, coupon);
        coupon.setCouponId(new CouponId(cmd.getId()));
        coupon.update();
        couponRepo.update(coupon);
        couponItemComService.create(cmd.getItems(), coupon);
    }

    /**
     * 删除优惠券
     *
     * @param id
     */
    public int delete(Long id) {
        return couponRepo.delete(id);
    }

    /**
     * 领取优惠券减库存
     *
     * @param couponId
     */
    public int cutStock(Long couponId) {
        Coupon coupon = couponQueryService.getById(couponId);
        if (null == coupon) {
            throw new IcBizException(IcReturnCode.IC201005.getCode(), IcReturnCode.IC201005.getMsg());
        }
        coupon.cutStock();
        return couponRepo.update(coupon);
    }

    /**
     * 使用优惠券
     *
     * @param coupon
     * @return int
     */
    @Transactional
    public int useCoupon(Coupon coupon) {
        if (null == coupon) {
            throw new IcBizException(IcReturnCode.IC201005.getCode(), IcReturnCode.IC201005.getMsg());
        }
        coupon.useCoupon();
        return couponRepo.updateCheckquantity(coupon);
    }

    /**
     * 判断优惠券是否在有效期
     *
     * @param id
     * @return Boolean false：不在有效期
     */
    public Boolean checkExpired(Long id) {
        Coupon coupon = couponQueryService.getByIdAll(id);
        if (null == coupon) {
            //throw new IcBizException(IcReturnCode.IC201005.getCode(), IcReturnCode.IC201005.getMsg());
            //卷被删除则不再有效期
            return false;
        }
        return coupon.checkExpired();
    }


    /**
     * 使用优惠券前校验
     *
     * @param couponId
     */
    public Coupon checkCoupon(Long memId, Long couponId, List<Long> targetIds, BigDecimal orderOriginalAmount) {
        Coupon coupon = couponQueryService.getById(couponId);
        if (null == coupon) {
            throw new IcBizException(IcReturnCode.IC201005.getCode(), IcReturnCode.IC201005.getMsg());
        }
        coupon.checkCoupon(memId, targetIds, orderOriginalAmount);
        return coupon;
    }

}
