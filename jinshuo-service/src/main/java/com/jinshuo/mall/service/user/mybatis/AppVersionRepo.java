package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.appVersion.AppVersion;
import com.jinshuo.mall.domain.user.model.appVersion.AppVersionId;
import com.jinshuo.mall.service.user.mybatis.mapper.AppVersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * app版本管理Repo
 *
 * @Classname AppVersionRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class AppVersionRepo {

    @Autowired(required = false)
    private AppVersionMapper mapper;

    /**
     * 生成id
     */
    public AppVersionId nextId() {
        return new AppVersionId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 查询全部
     *
     * @param appVersion
     * @return
     */
    public List<AppVersion> findAll(AppVersion appVersion) {
        return mapper.selectAll(appVersion);
    }

    /**
     * 保存或更新
     *
     * @param appVersion
     */
    public void save(AppVersion appVersion) {
        if (appVersion.getAppVersionId() == null) {
            appVersion.setAppVersionId(nextId());
            mapper.save(appVersion);
        } else {
            appVersion.preUpdate();
            mapper.update(appVersion);
        }
    }

    /**
     * 根据id查询记录
     *
     * @param id
     * @return
     */
    public AppVersion findById(Long id) {
        return mapper.findById(id);
    }

    /**
     * 查询最新版本
     *
     * @return
     */
    public AppVersion queryLatestAppVersion() {
        return mapper.queryLatestAppVersion();
    }

}

	
