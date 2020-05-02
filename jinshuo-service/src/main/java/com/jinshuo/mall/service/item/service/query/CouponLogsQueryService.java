package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.mall.domain.item.couponlogs.CouponLogs;
import com.jinshuo.mall.service.item.application.qry.CouponLogsQry;
import com.jinshuo.mall.service.item.mybatis.CouponLogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class CouponLogsQueryService {

    @Autowired
    private CouponLogsRepo couponLogsRepo;

    public CouponLogs getById(Long id) {
        return couponLogsRepo.queryById(id);
    }

    public Optional<CouponLogs> getOptionById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    public PageInfo getByPage(CouponLogsQry qry) {
        PageInfo pageInfo = couponLogsRepo.getByPage(qry);
        List<CouponLogs> tags = pageInfo.getList();
        return pageInfo;
    }

    /*
    * 根据优惠id查询我
    * */
    public List<CouponLogs> findByCouponId(Long memId,Long couponId) {
        return couponLogsRepo.queryMyLogsByCouponId(memId, couponId);
    }
}
