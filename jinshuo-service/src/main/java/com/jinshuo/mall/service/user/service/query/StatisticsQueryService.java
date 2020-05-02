package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.utils.MathUtil;
import com.jinshuo.mall.domain.user.model.statistics.Statistics;
import com.jinshuo.mall.domain.user.model.statistics.VisitorLog;
import com.jinshuo.mall.service.user.application.dto.StatisticsUserDto;
import com.jinshuo.mall.service.user.application.dto.VisitorListDto;
import com.jinshuo.mall.service.user.application.qry.StatisticsQry;
import com.jinshuo.mall.service.user.application.qry.VisitorCountQry;
import com.jinshuo.mall.service.user.mybatis.StatisticsRepo;
import com.jinshuo.mall.service.user.mybatis.VisitorLogRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class StatisticsQueryService {

    @Autowired
    private StatisticsRepo statisticsRepo;

    @Autowired
    private VisitorLogRepo visitorLogRepo;


    /**
     * 统计页面访问量
     *
     * @param qry
     */
    public StatisticsUserDto countVisitor(VisitorCountQry qry) {
        log.info("  --  统计页面访问量,输入参数，" + qry);
        //qry.setStatisticsCode("100001"); //默认统计首页
        List<Statistics> statisticsList = statisticsRepo.countStatistics(qry);
        Integer accessCount = 0;
        StatisticsUserDto dto = new StatisticsUserDto();
        dto.init();
        if (null == statisticsList || statisticsList.size() < 1) {
            return dto;
        }
        accessCount = statisticsList.stream().mapToInt(Statistics::getCount).sum();
        List<VisitorLog> visitorLogs = visitorLogRepo.countMember(qry);

        DecimalFormat df = new DecimalFormat("0.00");
        String result = "0";
        if (null != visitorLogs && visitorLogs.size() > 0) {
            result = df.format(accessCount / visitorLogs.size());
        }
        dto = StatisticsUserDto.builder()
                .accessCount(accessCount)
                .accessMember(visitorLogs.size())
                .averagePages(new Double(result))
                .build();
        return dto;
    }

    /**
     * 统计页面访问量列表
     *
     * @param qry
     */
    public PageInfo countVisitorList(StatisticsQry qry) {
        log.info("  --  统计页面访问量列表,输入参数，" + qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Statistics> list = statisticsRepo.countStatisticsList(qry);
        VisitorListDto visitorListDto;
        List<VisitorListDto> dtos = new ArrayList<>();
        for (Statistics statistics : list) {
            visitorListDto = new VisitorListDto();
            visitorListDto.setAccessCount(statistics.getCount());
            visitorListDto.setDate(statistics.getCreateDate());
            visitorListDto.setAccessMember(visitorLogRepo.queryByCreateDate(statistics.getCreateDate()).size());
            DecimalFormat df = new DecimalFormat("0.00");
            String result = "0.00";
            try {
                result = df.format(statistics.getCount() / visitorListDto.getAccessMember());
            } catch (Exception e) {
            }
            visitorListDto.setAveragePages(new Double(result));
            dtos.add(visitorListDto);
        }
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 统计转换率
     *
     * @param qry
     */
    public StatisticsUserDto conversionRate(VisitorCountQry qry) {
        log.info("  --  统计转换率,输入参数，" + qry);
        List<VisitorLog> visitorLogs = visitorLogRepo.countMember(qry);
        StatisticsUserDto dto = new StatisticsUserDto();
        dto.init();
        if (null == visitorLogs || visitorLogs.size() < 1) {
            return dto;
        }
        List<Long> userIds = visitorLogs.stream().map(VisitorLog::getUserId).filter(x -> x != null).collect(Collectors.toList());
        dto.setAccessMember(visitorLogs.size());
        dto.setRegisterMember(userIds.size());
        dto.setConversionRate(MathUtil.ADivideBPercent(new BigDecimal(userIds.size()), new BigDecimal(visitorLogs.size())));
        return dto;
    }


    /**
     * 统计转换率列表
     *
     * @param qry
     */
    public PageInfo conversionRateList(StatisticsQry qry) {
        log.info("  --  统计转换率列表,输入参数，" + qry);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<VisitorLog> list = visitorLogRepo.conversionRateList(qry);
        VisitorListDto visitorListDto;
        List<VisitorListDto> dtos = new ArrayList<>();
        for (VisitorLog visitorLog : list) {
            visitorListDto = new VisitorListDto();
            visitorListDto.setDate(visitorLog.getVisitLastTime());
            visitorListDto.setAccessMember(visitorLog.getVisitCount());
            visitorListDto.setRegisterMember(visitorLogRepo.conversionRateListRegist(visitorLog.getVisitLastTime()));
            visitorListDto.setConversionRate(MathUtil.ADivideBPercent(new BigDecimal(visitorListDto.getRegisterMember()), new BigDecimal(visitorListDto.getAccessMember())));
            dtos.add(visitorListDto);
        }
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(dtos);
        return pageInfo;
    }
}
