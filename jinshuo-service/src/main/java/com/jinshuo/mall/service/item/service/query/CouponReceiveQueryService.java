package com.jinshuo.mall.service.item.service.query;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import com.jinshuo.mall.domain.item.couponreceive.CouponReceive;
import com.jinshuo.mall.service.item.application.assermbler.UserCouponReceiveDtoAssembler;
import com.jinshuo.mall.service.item.application.cmd.TargetCmd;
import com.jinshuo.mall.service.item.application.dto.SkuDto;
import com.jinshuo.mall.service.item.application.dto.UserCouponReceiveDto;
import com.jinshuo.mall.service.item.application.qry.CouponReceiveQry;
import com.jinshuo.mall.service.item.application.qry.OrderCouponQry;
import com.jinshuo.mall.service.item.mybatis.CouponReceiveRepo;
import com.jinshuo.mall.service.item.service.command.CouponReceiveComService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class CouponReceiveQueryService {

    @Autowired
    private CouponReceiveRepo couponReceiveRepo;

    @Autowired
    private CouponQueryService couponQueryService;

    @Autowired
    private CouponReceiveComService couponReceiveComService;

    @Autowired
    private CouponItemQueryService couponItemQueryService;

    @Autowired
    private SkuQueryService skuQueryService;

    public CouponReceive getById(Long id) {
        return couponReceiveRepo.queryById(id);
    }

    public Optional<CouponReceive> getOptionById(Long id) {
        return Optional.ofNullable(getById(id));
    }


    /**
     * 分页查询我的优惠券
     *
     * @param qry
     * @return PageInfo
     */
    public PageInfo getByPage(CouponReceiveQry qry) {
        couponReceiveComService.changUseStatus();
        qry.setMemId(UserIdUtils.getUserId());
        PageInfo pageInfo = null;

        //判断是否根据产品查询
        if (null != qry.getTargetId()) {
            List<CouponItem> items = couponItemQueryService.findByTargetId(qry.getTargetId());
            if (null != items && items.size() > 0) {
                qry.setCouponIds(items.stream().map(couponItem -> couponItem.getCouponItemId().getId()).collect(Collectors.toList()));
            }

        }
        pageInfo = couponReceiveRepo.getByPage(qry);
        List<CouponReceive> couponReceives = pageInfo.getList();
        List<UserCouponReceiveDto> dtos = couponReceives.stream().map(couponReceive -> {
            Coupon coupon = couponQueryService.getById(couponReceive.getCouponId());
            return UserCouponReceiveDtoAssembler.assembleUserSkuDto(couponReceive, coupon);
        }).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    public List<CouponReceive> findByExmple(CouponReceive couponReceive) {
        return couponReceiveRepo.queryByExmple(couponReceive);
    }

    public List<CouponReceive> findByCouponId(Long couponId) {
        CouponReceive couponReceive = CouponReceive.builder()
                .couponId(couponId)
                .memId(UserIdUtils.getUserId())
                .build();
        return couponReceiveRepo.queryByExmple(couponReceive);
    }


    /**
     * 查询我的未使用优惠卷数
     *
     * @return Integer
     */
    public Integer getMyCouponsCount() {
        Integer count = 0;
        couponReceiveComService.changUseStatus();
        CouponReceiveQry qry = new CouponReceiveQry();
        qry.setMemId(UserIdUtils.getUserId());
        qry.setUseStatus(0);
        PageInfo<CouponReceive> pageInfo = couponReceiveRepo.getByPage(qry);
        count = pageInfo.getSize();
        return count;
    }

    /**
     * 根据订单信息查询可用的优惠券
     *
     * @return Integer
     */
    public PageInfo getByOrderInfo(OrderCouponQry orderCouponQry) {
        log.info(" -- 根据订单信息查询可用的优惠券,输入参数：" + JSONObject.toJSONString(orderCouponQry));
        BigDecimal amount = new BigDecimal(0);
        if (null != orderCouponQry && null != orderCouponQry.getTargetCmds() && orderCouponQry.getTargetCmds().size() > 0) {
            amount = skuQueryService.getAmountBySkuId(orderCouponQry.getTargetCmds());
        }
        CouponReceiveQry qry = new CouponReceiveQry();
        qry.setMemId(UserIdUtils.getUserId());
        qry.setPageNum(orderCouponQry.getPageNum());
        qry.setPageSize(orderCouponQry.getPageSize());
        qry.setAmount(amount);
        PageInfo pageInfo = null;

        //判断是否根据产品查询 不可用
        List<Long> couponIds = new ArrayList<>();
        couponIds.add(1l);
        for (TargetCmd cmd : orderCouponQry.getTargetCmds()) {
            SkuDto skuDto = skuQueryService.getSkuDtoBySkuId(cmd.getTargetId());
            List<CouponItem> items = couponItemQueryService.findByTargetId(Long.parseLong(skuDto.getSpuId()));
            for (CouponItem item : items) {
                couponIds.add(item.getCouponId().getId());
            }
        }
        qry.setCouponIds(couponIds);
        log.info("--开始根据订单信息查询我的可用优惠券脚本,输入参数：" + JSONObject.toJSONString(qry));
        pageInfo = couponReceiveRepo.queryMyCouponBySpu(qry);
        List<CouponReceive> couponReceives = pageInfo.getList();
        List<UserCouponReceiveDto> dtos = couponReceives.stream().map(couponReceive -> {
            Coupon coupon = couponQueryService.getById(couponReceive.getCouponId());
            return UserCouponReceiveDtoAssembler.assembleUserSkuDto(couponReceive, coupon);
        }).collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }
}
