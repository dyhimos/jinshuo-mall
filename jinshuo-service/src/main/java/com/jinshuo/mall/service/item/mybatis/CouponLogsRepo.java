package com.jinshuo.mall.service.item.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.couponlogs.CouponLogs;
import com.jinshuo.mall.service.item.application.qry.CouponLogsQry;
import com.jinshuo.mall.service.item.mybatis.mapper.CouponLogsMapper;
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
public class CouponLogsRepo {

    @Autowired(required = false)
    private CouponLogsMapper couponLogsMapper;


    public void save(CouponLogs couponLogs) {
        couponLogsMapper.create(couponLogs);
    }

    public int update(CouponLogs couponLogs) {
        return couponLogsMapper.update(couponLogs);
    }

    public CouponLogs queryById(Long couponLogsId) {
        return couponLogsMapper.queryById(couponLogsId);
    }

    public List<CouponLogs> findAll() {
        List<CouponLogs> list = couponLogsMapper.findAll();
        return list;
    }

    public int delete(Long couponLogsId) {
        return couponLogsMapper.delete(couponLogsId);
    }

    public PageInfo<CouponLogs> getByPage(CouponLogsQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<CouponLogs> list = couponLogsMapper.findAll();
        PageInfo<CouponLogs> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<CouponLogs> queryMyLogsByCouponId(Long memId,Long couponId) {
        List<CouponLogs> list = couponLogsMapper.queryMyLogsByCouponId(memId,couponId);
        return list;
    }
}
