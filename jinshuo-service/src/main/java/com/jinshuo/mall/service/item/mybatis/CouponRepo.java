package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.service.item.application.qry.CouponQry;
import com.jinshuo.mall.service.item.mybatis.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dyh
 */
@Repository
public class CouponRepo {

    @Autowired(required = false)
    private CouponMapper couponMapper;

    public void save(Coupon coupon) {
        couponMapper.create(coupon);
    }

    public int update(Coupon coupon) {
        return couponMapper.update(coupon);
    }

    public Coupon queryById(Long couponId) {
        return couponMapper.queryById(couponId);
    }

    public int delete(Long couponId) {
        return couponMapper.delete(couponId);
    }

    public List<Coupon> getByPage(CouponQry qry) {

        List<Coupon> list = couponMapper.findAll(qry);
        return list;
    }

    public PageInfo<Coupon> getByDynamic(CouponQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Coupon> list = couponMapper.getByDynamic(qry);
        PageInfo<Coupon> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public Coupon getByIdAll(Long couponId) {
        return couponMapper.getByIdAll(couponId);
    }

    public int updateCheckquantity(Coupon coupon) {
        return couponMapper.updateCheckquantity(coupon);
    }

    public List<Coupon> queryByExmple(Coupon coupon) {
        return couponMapper.queryByExmple(coupon);
    }
}
