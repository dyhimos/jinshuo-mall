package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.loginreport.LoginReport;
import com.jinshuo.mall.service.user.application.qry.ManagerCountQry;
import com.jinshuo.mall.service.user.mybatis.mapper.LoginReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class LoginReportRepo {

    @Autowired(required = false)
    private LoginReportMapper loginReportMapper;

    public int save(LoginReport loginReport) {
        return loginReportMapper.create(loginReport);
    }

    public int update(LoginReport loginReport) {
        return loginReportMapper.update(loginReport);
    }

    public LoginReport queryTodayReport(Long id) {
        return loginReportMapper.queryTodayReport(id);
    }

    public List<LoginReport> countLogin(ManagerCountQry qry) {
        return loginReportMapper.countLogin(qry);
    }
}
