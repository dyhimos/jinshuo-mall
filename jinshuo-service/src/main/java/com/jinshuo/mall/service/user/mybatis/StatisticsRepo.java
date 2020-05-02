package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.statistics.Statistics;
import com.jinshuo.mall.service.user.application.qry.StatisticsQry;
import com.jinshuo.mall.service.user.application.qry.VisitorCountQry;
import com.jinshuo.mall.service.user.mybatis.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 19458 on 2020/1/2.
 */
@Repository
public class StatisticsRepo {

    @Autowired(required = false)
    private StatisticsMapper statisticsMapper;

    public int save(Statistics statistics) {
        return statisticsMapper.create(statistics);
    }

    public Statistics queryById(Long id) {
        return statisticsMapper.queryById(id);
    }

    /**
     * 查询今天是否已经有访问记录
     *
     * @param statisticsCode
     * @return
     */
    public Statistics queryByCode(String statisticsCode) {
        return statisticsMapper.queryByCode(statisticsCode);
    }

    public int delete(Long id) {
        return statisticsMapper.delete(id);
    }

    public int updateCount(Statistics statistics) {
        return statisticsMapper.updateCount(statistics);
    }

    public List<Statistics> countStatistics(VisitorCountQry qry){
        return statisticsMapper.countStatistics(qry);
    }

    public List<Statistics> countStatisticsList(StatisticsQry qry){
        return statisticsMapper.countStatisticsList(qry);
    }
}
