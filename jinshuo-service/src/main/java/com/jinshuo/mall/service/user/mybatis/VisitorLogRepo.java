package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.statistics.VisitorLog;
import com.jinshuo.mall.service.user.application.dto.UserVisitorLogDto;
import com.jinshuo.mall.service.user.application.qry.StatisticsQry;
import com.jinshuo.mall.service.user.application.qry.UserVisitorLogQry;
import com.jinshuo.mall.service.user.application.qry.VisitorCountQry;
import com.jinshuo.mall.service.user.mybatis.mapper.VisitorLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2020/1/2.
 */
@Repository
public class VisitorLogRepo {

    @Autowired
    private VisitorLogMapper visitorLogMapper;


    public int save(VisitorLog visitorLog) {
        return visitorLogMapper.create(visitorLog);
    }

    public VisitorLog queryByIpAddr(String id) {
        return visitorLogMapper.queryByIpAddr(id);
    }

    public VisitorLog queryByIpUserId(Long userId) {
        return visitorLogMapper.queryByIpUserId(userId);
    }

    public int updateCount(VisitorLog visitorLog) {
        return visitorLogMapper.updateCount(visitorLog);
    }

    public List<VisitorLog> countMember(VisitorCountQry qry) {
        return visitorLogMapper.countMember(qry);
    }

    public List<VisitorLog> queryByCreateDate(Date date) {
        return visitorLogMapper.queryByCreateDate(date);
    }

    public List<VisitorLog> conversionRateList(StatisticsQry qry) {
        return visitorLogMapper.conversionRateList(qry);
    }

    public Integer conversionRateListRegist(Date date) {
        return visitorLogMapper.conversionRateListRegist(date);
    }
    /**
     * 查询全部日志列表
     * @param query
     * @return
     */
    public List<UserVisitorLogDto> findAll(UserVisitorLogQry query) {
        return visitorLogMapper.findAll(query);
    }

}
