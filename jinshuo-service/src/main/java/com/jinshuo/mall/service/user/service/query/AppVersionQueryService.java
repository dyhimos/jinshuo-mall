package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class AppVersionQueryService{

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
     * 查询最新的版本
     * @return
     */
    public AppVersionDto queryLatestAppVersion() {
        AppVersionDto appVersionDto = new AppVersionDto();
        AppVersion appVersion = appVersionRepo.queryLatestAppVersion();
        BeanUtils.copyProperties(appVersion,appVersionDto);
        return appVersionDto;
    }


}

	
