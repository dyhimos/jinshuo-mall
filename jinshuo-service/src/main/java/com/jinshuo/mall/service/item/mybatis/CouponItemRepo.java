package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.mybatis.mapper.CouponItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname CouponItemRepo
 * @Description TODO
 * @Date 2019/7/30 20:07
 * @Created by dyh
 */
@Repository
public class CouponItemRepo {

    @Autowired(required = false)
    private CouponItemMapper couponItemMapper;


    public void save(CouponItem tag) {
        couponItemMapper.create(tag);
    }

    public int update(CouponItem tag) {
        return couponItemMapper.update(tag);
    }


    public CouponItem queryById(Long couponItemId) {
        return couponItemMapper.queryById(couponItemId);
    }


    public List<CouponItem> findAll() {
        List<CouponItem> list = couponItemMapper.findAll();
        return list;
    }

    public List<CouponItem> queryByCouponId(Long couponId) {
        return couponItemMapper.queryByCouponId(couponId);
    }

    public int delete(Long tagId) {
        return couponItemMapper.delete(tagId);
    }

    public int deleteByCouponId(Long couponId) {
        return couponItemMapper.deleteByCouponId(couponId);
    }

    public PageInfo<CouponItem> getByPage(TagQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<CouponItem> list = couponItemMapper.findAll();
        PageInfo<CouponItem> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<CouponItem> queryByTargetId(Long targetId) {
        return couponItemMapper.queryByTargetId(targetId);
    }

    public CouponItem checkCoupon(Long couponId,Long targetId) {
        return couponItemMapper.checkCoupon(couponId,targetId);
    }

    public List<CouponItem> findByExmple(CouponItem couponItem){
        return couponItemMapper.queryByExmple(couponItem);
    }
}
