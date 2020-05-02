package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import com.jinshuo.mall.domain.item.couponitem.CouponItemId;
import com.jinshuo.mall.service.item.application.cmd.CouponItemCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.CouponItemUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.CouponItemRepo;
import com.jinshuo.mall.service.item.service.query.CouponItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class CouponItemComService {

    @Autowired
    private CouponItemRepo couponItemRepo;

    @Autowired
    private CouponItemQueryService couponItemQueryService;


    /**
     * 新增优惠券绑定关系
     *
     * @param cmds
     */
    public void create(List<CouponItemCreateCmd> cmds, Coupon coupon) {
        couponItemRepo.deleteByCouponId(coupon.getCouponId().getId());
        Integer isApply = 0;
        if (3 == coupon.getScopeType()) {
            isApply = 1;
        }
        for (CouponItemCreateCmd cmd : cmds) {
            CouponItem couponItem = CouponItem.builder()
                    .couponItemId(new CouponItemId(CommonSelfIdGenerator.generateId()))
                    .couponId(coupon.getCouponId())
                    .type(cmd.getType())
                    .isApply(isApply)
                    .targetId(cmd.getTargetId())
                    .build();
            couponItem.preInsert();
            couponItemRepo.save(couponItem);
        }

    }

    /**
     * 新增优惠券绑定关系
     *
     * @param cmd
     */
    public void create(CouponItemCreateCmd cmd) {
        CouponItem couponItem = CouponItem.builder()
                .couponItemId(new CouponItemId(CommonSelfIdGenerator.generateId()))
                //.couponId(new CouponId(cmd.getCouponId()))
                .type(cmd.getType())
                .isApply(cmd.getIsApply())
                .targetId(cmd.getTargetId())
                .build();
        couponItem.preInsert();
        couponItemRepo.save(couponItem);
    }

    /**
     * 修改优惠券绑定关系
     *
     * @param cmd
     */
    public void update(CouponItemUpdateCmd cmd) {
        couponItemQueryService.getOptionById(cmd.getId())
                .map(couponItem -> couponItem.update(cmd.getCouponId(), cmd.getTargetId(), cmd.getType(), cmd.getIsApply()))
                .ifPresent(tag -> couponItemRepo.update(tag));
    }

    public int delete(Long id) {
        return couponItemRepo.delete(id);
    }
}
