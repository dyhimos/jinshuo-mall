package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.utils.DateUtils;
import com.jinshuo.mall.domain.user.model.loginreport.LoginReport;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.service.order.application.dto.OrderCountInfoDto;
import com.jinshuo.mall.service.user.application.qry.ManagerCountQry;
import com.jinshuo.mall.service.user.mybatis.LoginReportRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class LoginReportComService {

    @Autowired
    private LoginReportRepo loginReportRepo;


    /**
     * 汇总记录登录信息
     *
     * @param userAccount
     */
    public void recordingLogin(UserAccount userAccount) {
        log.info(" -- 汇总记录登录信息" + JSONObject.toJSONString(userAccount));
        LoginReport loginReport = loginReportRepo.queryTodayReport(userAccount.getShopId());
        if (null == loginReport) {
            loginReport = new LoginReport();
            loginReport.init(userAccount.getShopId());
            loginReportRepo.save(loginReport);
        } else {
            loginReport.addTimes();
            Date today = DateUtils.getToday();
            if (null == userAccount.getLastLoginAt() || today.after(userAccount.getLastLoginAt())) {
                loginReport.setMemberCount(loginReport.getMemberCount() + 1);
            }
            loginReportRepo.update(loginReport);
        }
    }


    /**
     * 汇总登录信息
     *
     * @param qry
     * @return
     */
    public OrderCountInfoDto queryCount(ManagerCountQry qry) {
        log.info(" -- 汇总登录信息" + JSONObject.toJSONString(qry));
        OrderCountInfoDto dto = new OrderCountInfoDto();
        dto.init();
        List<LoginReport> loginReportList = loginReportRepo.countLogin(qry);
        if (null != loginReportList && loginReportList.size() > 0) {
            dto.setAccessMember(loginReportList.stream().mapToInt(LoginReport::getMemberCount).sum());
            dto.setAccessCount(loginReportList.stream().mapToInt(LoginReport::getAccessCount).sum());
            dto.setAccessMan(dto.getAccessMember());
        }
        return dto;
    }

}
