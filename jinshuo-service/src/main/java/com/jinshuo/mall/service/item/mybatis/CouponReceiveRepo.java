package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.couponreceive.CouponReceive;
import com.jinshuo.mall.service.item.application.qry.CouponReceiveQry;
import com.jinshuo.mall.service.item.mybatis.mapper.CouponReceiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dyh
 */
@Repository
public class CouponReceiveRepo {

    @Autowired(required = false)
    private CouponReceiveMapper couponReceiveMapper;


    public int save(CouponReceive tag) {
        return couponReceiveMapper.create(tag);
    }

    public int update(CouponReceive tag) {
        return couponReceiveMapper.update(tag);
    }

    public CouponReceive queryById(Long couponReceiveId) {
        return couponReceiveMapper.queryById(couponReceiveId);
    }

    public int delete(Long tagId) {
        return couponReceiveMapper.delete(tagId);
    }

    public PageInfo<CouponReceive> getByPage(CouponReceiveQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<CouponReceive> list = couponReceiveMapper.findAll(qry);
        PageInfo<CouponReceive> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<CouponReceive> queryByExmple(CouponReceive couponReceive) {
        return couponReceiveMapper.queryByExmple(couponReceive);
    }

    public int invalidCoupon(Long id, Integer useState) {
        return couponReceiveMapper.invalidCoupon(id, useState);
    }

    public int invalidCouponWithTime(Long id, Integer useState,Date date) {
        return couponReceiveMapper.invalidCouponWithTime(id, useState,date);
    }


    /*
    * 根据订单信息查询我的可使用优惠券
    * */
    public PageInfo queryMyCouponBySpu(CouponReceiveQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<CouponReceive> list = couponReceiveMapper.queryMyCouponBySpu(qry);
        PageInfo<CouponReceive> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    ;
}
