package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.core.utils.IpUtil;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.statistics.VisitorLog;
import com.jinshuo.mall.service.user.mybatis.VisitorLogRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2020/4/2.
 */
@Slf4j
@Service
public class VisitorLogComService {

    @Autowired
    private VisitorLogRepo visitorLogRepo;


    /**
     * 记录访客
     *
     * @param request
     */
    public void saveVisitorLog(HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        Long userId = null;
        try {
            userId = UserIdUtils.getUserId();
        } catch (Exception e) {
        }
        VisitorLog visitorLog = null;
        if (null != userId) {
            visitorLog = visitorLogRepo.queryByIpUserId(userId);
        } else {
            visitorLog = visitorLogRepo.queryByIpAddr(ipAddr);
        }
        if (null != visitorLog) {
            visitorLog.setIpAddr(ipAddr);
            visitorLog.addCount();
            visitorLogRepo.updateCount(visitorLog);
            return;
        }
        visitorLog = VisitorLog.builder()
                .visitFirstTime(new Date())
                .visitLastTime(new Date())
                .ipAddr(ipAddr)
                .build();
        visitorLog.init();
        if (null != userId) {
            visitorLog.setUserId(userId);
        }
        visitorLogRepo.save(visitorLog);
    }
}
