package com.jinshuo.mall.service.item.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.item.couponreceive.CouponReceive;
import com.jinshuo.mall.service.item.application.cmd.CouponReceiveCreateCmd;
import com.jinshuo.mall.service.item.mybatis.CouponReceiveRepo;
import com.jinshuo.mall.service.item.service.query.CouponQueryService;
import com.jinshuo.mall.service.item.service.query.CouponReceiveQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class CouponReceiveComService {

    @Autowired
    private CouponReceiveRepo couponReceiveRepo;

    @Autowired
    private CouponComService couponComService;

    @Autowired
    private CouponReceiveQueryService couponReceiveQueryService;

    @Autowired
    private CouponQueryService couponQueryService;

    /**
     * 领取优惠券
     *
     * @param cmd
     */
    @Transactional
    public int create(CouponReceiveCreateCmd cmd) {
        log.info(" -- 领取（后台发送）优惠券,输入参数：" + JSONObject.toJSONString(cmd));
        checkReceive(cmd.getCouponId(), UserIdUtils.getUserId());
        CouponReceive couponReceive = new CouponReceive();
        BeanUtils.copyProperties(cmd, couponReceive);
        couponReceive.insert();
        couponComService.cutStock(cmd.getCouponId());
        return couponReceiveRepo.save(couponReceive);
    }

    /**
     * 将我名下过期的优惠券至于失效
     *
     * @return int
     */
    public int changUseStatus() {
        CouponReceive couponReceive = new CouponReceive();
        couponReceive.setMemId(UserIdUtils.getUserId());
        couponReceive.setUseStatus(0);
        List<CouponReceive> list = couponReceiveQueryService.findByExmple(couponReceive);
        if (null == list) {
            return 0;
        }
        list.forEach(couponReceive1 -> {
            if (!couponComService.checkExpired(couponReceive1.getCouponId())) {
                couponReceiveRepo.invalidCoupon(couponReceive1.getCouponReceiveId().getId(), -1);
            }
        });
        return 1;
    }


    /**
     * 使用优惠券
     *
     * @param couponReceiveId
     * @return int
     */
    @Transactional
    public int useCoupon(Long couponReceiveId) {
        CouponReceive couponReceive = couponReceiveQueryService.getById(couponReceiveId);
        if (null == couponReceive) {
            throw new IcBizException(IcReturnCode.IC201005.getCode(), IcReturnCode.IC201005.getMsg());
        }
        couponReceive.checkUseStatus();
        couponReceiveRepo.invalidCouponWithTime(couponReceiveId, 1, new Date());
        return 0;
    }


    /**
     * 自动领取优惠券
     *
     * @param cmd
     */
    @Transactional
    public int autoCreate(CouponReceiveCreateCmd cmd) {
        Coupon temp = Coupon.builder().forPeople(1).gainMethod(2).shopId(10088l).build();
        List<Coupon> coupons = couponQueryService.getByExmple(temp);
        if (null == coupons || coupons.size() < 1) {
            return 0;
        }
        CouponReceive couponReceive;
        for (Coupon coupon : coupons) {
            checkReceive(coupon.getCouponId().getId(), cmd.getMemId());
            couponReceive = CouponReceive.builder().couponId(coupon.getCouponId().getId()).memId(cmd.getMemId()).build();
            couponReceive.insert(cmd.getMemId());
            couponComService.cutStock(coupon.getCouponId().getId());
            return couponReceiveRepo.save(couponReceive);
        }
        return coupons.size();
    }


    /**
     * 校验优惠券是否领取
     *
     * @param couponId
     */
    public void checkReceive(Long couponId, Long memId) {
        CouponReceive couponReceive = CouponReceive.builder().memId(memId).couponId(couponId).build();
        List<CouponReceive> coupons = couponReceiveQueryService.findByExmple(couponReceive);
        if (coupons.size() > 0) {
            //已领取，报错
            throw new IcBizException(IcReturnCode.IC201018.getCode(), IcReturnCode.IC201018.getMsg());
        }
    }


    /**
     * 领取优惠券
     *
     * @param cmd
     */
    @Transactional
    public int managerCreate(CouponReceiveCreateCmd cmd) {
        log.info(" -- 后台发送优惠券,输入参数：" + JSONObject.toJSONString(cmd));
        if (null == cmd.getMemId()) {
            throw new IcBizException(IcReturnCode.IC201019.getCode(), IcReturnCode.IC201019.getMsg());
        }
        checkReceive(cmd.getCouponId(), cmd.getMemId());
        CouponReceive couponReceive = new CouponReceive();
        BeanUtils.copyProperties(cmd, couponReceive);
        couponReceive.insert(cmd.getMemId());
        couponComService.cutStock(cmd.getCouponId());
        return couponReceiveRepo.save(couponReceive);
    }


}
