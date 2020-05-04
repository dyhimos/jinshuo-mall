package com.jinshuo.mall.service.item.service.query;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import com.jinshuo.mall.domain.item.couponreceive.CouponReceive;
import com.jinshuo.mall.service.item.application.assermbler.CouponAssembler;
import com.jinshuo.mall.service.item.application.dto.CouponDto;
import com.jinshuo.mall.service.item.application.dto.UserCouponDto;
import com.jinshuo.mall.service.item.application.qry.CouponQry;
import com.jinshuo.mall.service.item.mybatis.CouponRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class CouponQueryService {

    @Autowired
    private CouponRepo couponRepo;

    @Autowired
    private CouponItemQueryService couponItemQueryService;

    @Autowired
    private CouponReceiveQueryService couponReceiveQueryService;


    public Coupon getById(Long id) {
        return couponRepo.queryById(id);
    }

    public Coupon getByIdAll(Long id) {
        return couponRepo.getByIdAll(id);
    }

    public Optional<Coupon> getOptionById(Long id) {
        return Optional.ofNullable(getById(id));
    }


    public CouponDto getDtoById(Long id) {
        Coupon coupon = getById(id);
        CouponDto dto = CouponAssembler.assembleCouponDto(coupon);
        dto.changeItems(couponItemQueryService.findDtosByCouponId(coupon.getCouponId().getId()));
        return dto;
    }

    public PageInfo getDtoByPage(CouponQry qry) {
        log.info(" -- 后端查询优惠券列表，输入参数" + JSONObject.toJSONString(qry));
        if (null == qry.getShopId()) {
            qry.setShopId(DefaultShopId.SHOPID);
        }
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        Coupon temp = Coupon.builder().shopId(qry.getShopId()).build();
        List<Coupon> coupons = couponRepo.queryByExmple(temp);
        PageInfo pageInfo = new PageInfo(coupons);
        List<CouponDto> dtos = coupons.stream()
                .map(coupon -> CouponAssembler.assembleCouponDto(coupon))
                .map(couponDto -> couponDto.changeItems(couponItemQueryService.findDtosByCouponId(Long.parseLong(couponDto.getId()))))
                .collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }


    /**
     * 客户端查询可领取的优惠券列表
     *
     * @param qry
     * @return PageInfo
     */
    public PageInfo getUserDtoByPage(CouponQry qry) {
        //qry.setCouponIds(null);
        // 根据产品ID查询优惠券设置的可使用此产品或屏蔽的此产品优惠券id
        if (null != qry.getTargetId()) {
            List<CouponItem> items = couponItemQueryService.findByTargetId(qry.getTargetId());
            if (null != items && items.size() > 0) {
                qry.setCouponIds(items.stream().map(couponItem -> couponItem.getCouponItemId().getId()).collect(Collectors.toList()));
            }
        }
        PageInfo pageInfo = couponRepo.getByDynamic(qry);
        List<Coupon> coupons = pageInfo.getList();
        List<UserCouponDto> dtos = coupons.stream()
                .map(coupon -> CouponAssembler.assembleUserDto(coupon))
                .map(couponDto -> couponDto.changeItems(couponItemQueryService.findDtosByCouponId(Long.parseLong(couponDto.getId()))))
                .map(couponDto -> {
                    List<CouponReceive> list = couponReceiveQueryService.findByCouponId(Long.parseLong(couponDto.getId()));
                    if (null != list && list.size() > 0) {
                        couponDto.setReceivedStatus(0);
                    } else {
                        couponDto.setReceivedStatus(1);
                    }
                    return couponDto;
                })
                .collect(Collectors.toList());
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 查询优惠券详情
     *
     * @param id
     * @return PageInfo
     */
    public UserCouponDto getUserDtoById(Long id) {
        CouponReceive couponReceive = couponReceiveQueryService.getById(id);
        if (null == couponReceive) {
            return null;
        }
        Coupon coupon = getById(couponReceive.getCouponId());
        if (null == coupon) {
            return null;
        }
        UserCouponDto dto = CouponAssembler.assembleUserDto(coupon);
        dto.changeItems(couponItemQueryService.findDtosByCouponId(coupon.getCouponId().getId()));
        return dto;
    }

    public List<Coupon> getByExmple(Coupon coupon) {
        return couponRepo.queryByExmple(coupon);
    }
}
