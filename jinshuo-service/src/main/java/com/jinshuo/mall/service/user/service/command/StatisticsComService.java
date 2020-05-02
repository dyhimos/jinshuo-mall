package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.mall.domain.user.model.statistics.Statistics;
import com.jinshuo.mall.service.user.application.cmd.StatisticsCmd;
import com.jinshuo.mall.service.user.mybatis.StatisticsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 页面访问统计
 * @Author: dongyh
 * @CreateDate: 2019/12/13 14:10
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/12/13 14:10
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
@Service
public class StatisticsComService {

    @Autowired
    private StatisticsRepo statisticsRepo;

    @Autowired
    private VisitorLogComService visitorLogComService;


    /**
     * 统计页面访问量
     *
     * @param cmd
     */
    public void saveStatistics(StatisticsCmd cmd, HttpServletRequest request) {
        log.info("  --  统计页面访问量,输入参数，" + cmd);
        Statistics statistics = statisticsRepo.queryByCode(cmd.getStatisticsCode());
        try {
            visitorLogComService.saveVisitorLog(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (statistics == null) {
            statistics = Statistics.builder()
                    .statisticsType(cmd.getStatisticsType())
                    .statisticsCode(cmd.getStatisticsCode())
                    .build();
            statistics.init();
            statisticsRepo.save(statistics);
            return;
        }
        statistics.addCount();
        statisticsRepo.updateCount(statistics);
    }
}
