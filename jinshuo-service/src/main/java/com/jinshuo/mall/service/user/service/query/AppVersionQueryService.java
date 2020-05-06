package com.jinshuo.mall.service.user.service.query;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.mall.domain.user.model.appVersion.AppVersion;
import com.jinshuo.mall.service.user.application.assermbler.AppVersionAssermbler;
import com.jinshuo.mall.service.user.application.dto.AppVersionDto;
import com.jinshuo.mall.service.user.application.qry.AppVersionQry;
import com.jinshuo.mall.service.user.mybatis.AppVersionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AppVersionQueryService {

	@Autowired
	private AppVersionRepo appVersionRepo;

	 /**
     * 查询列表
     * @param query
     */
    public PageInfo<AppVersionDto> queryList(AppVersionQry query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        AppVersion appVersionQry = new  AppVersion();
        BeanUtils.copyProperties(query,appVersionQry);
        List<AppVersion> appVersionList = appVersionRepo.findAll(appVersionQry);
        List<AppVersionDto> appVersionDtos = new ArrayList<>();
        for (AppVersion appVersion : appVersionList) {
            appVersionDtos.add(AppVersionAssermbler.appVersionDto(appVersion));
        }
        PageInfo pageInfo = new PageInfo<>(appVersionList);
        pageInfo.setList(appVersionDtos);
        return pageInfo;
    }


    /**
     * 下载最新app
     * @return
     */
    public AppVersionDto downLatestAppVersion() {
        AppVersionDto appVersionDto = new AppVersionDto();
        AppVersion appVersion = appVersionRepo.queryLatestAppVersion();
        BeanUtils.copyProperties(appVersion,appVersionDto);
        appVersionDto.setId(appVersion.getAppVersionId().getId());
        appVersionDto.setUpdateWay(0);
        return appVersionDto;
    }


    /**
     * 检查是否要更新
     * @return
     */
    public AppVersionDto queryLatestAppVersion(Integer version) {
        log.info("更新申请的版本为：{}",version);
        AppVersionDto appVersionDto = new AppVersionDto();
        AppVersion appVersion = appVersionRepo.queryLatestAppVersion();
        BeanUtils.copyProperties(appVersion,appVersionDto);
        appVersionDto.setId(appVersion.getAppVersionId().getId());
        //判断是否要更新或者更新方式
        Integer latestVersion = appVersion.getAppVersion();
        Integer hl = latestVersion/100;
        Integer hv = version/100;
        Integer updateWay =0;

        if(hv ==0){
            throw new UcBizException(UcReturnCode.UC200044.getMsg(),UcReturnCode.UC200044.getCode());
        }
        //如果当前版本号<数据库版本号  否则不更新
        else if(latestVersion > version){
            //如果大版本号一样，则需热更新
            if(hv == hl){
                updateWay = 2;
            }
            //如果大版本号不一样，则需全更新
            else if(hv < hl){
                updateWay = 1;
            }
        }
        appVersionDto.setUpdateWay(updateWay);
        log.info("更新版本返回参数为：{}", JSON.toJSONString(appVersionDto));
        return appVersionDto;
    }
}

	
