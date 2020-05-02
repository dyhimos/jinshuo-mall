package com.jinshuo.mall.service.user.service.command;

import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.model.enums.Status;
import com.jinshuo.mall.domain.user.model.appVersion.AppVersion;
import com.jinshuo.mall.domain.user.model.appVersion.AppVersionId;
import com.jinshuo.mall.service.user.application.cmd.AppVersionCmd;
import com.jinshuo.mall.service.user.mybatis.AppVersionRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppVersionCmdService {

    @Autowired
    private AppVersionRepo appVersionRepo;

    /**
     * 保存/更新app版本管理
     *
     * @param cmd
     */
    public void save(AppVersionCmd cmd) {
        AppVersion appVersion = AppVersion.builder()
                /** 安卓下载地址*/
                .androidDownloadAddress(cmd.getAndroidDownloadAddress())
                /** 苹果下载地址*/
                .iosDownloadAddress(cmd.getIosDownloadAddress())
                /**app版本号*/
                .appVersion(cmd.getAppVersion())
                .build();
        if (cmd.getId() != null) {
            Long appVersionId = cmd.getId();
            //查询是否存在
            AppVersion isExit = appVersionRepo.findById(appVersionId);
            if (isExit == null) {
                throw new UcBizException(UcReturnCode.UC200043.getMsg(), UcReturnCode.UC200043.getCode());
            }

            appVersion.setAppVersionId(new AppVersionId(appVersionId));
            if (StringUtils.isNotBlank(cmd.getAndroidDownloadAddress())) {
                appVersion.setAndroidDownloadAddress(cmd.getAndroidDownloadAddress());
            }
            if (StringUtils.isNotBlank(cmd.getIosDownloadAddress())) {
                appVersion.setIosDownloadAddress(cmd.getIosDownloadAddress());
            }
            if (cmd.getAppVersion() != null) {
                appVersion.setAppVersion(cmd.getAppVersion());
            }

        } else {
            appVersion.preInsert();
        }
        appVersionRepo.save(appVersion);
    }

    /**
     * 删除app版本管理
     *
     * @param cmd
     */
    public void delete(AppVersionCmd cmd) {
        Long appVersionId = cmd.getId();
        //查询是否存在
        AppVersion appVersion = appVersionRepo.findById(appVersionId);
        if (appVersion == null) {
            throw new UcBizException(UcReturnCode.UC200043.getMsg(), UcReturnCode.UC200043.getCode());
        }
        appVersion.setStatus(Status.DELETE);
        appVersionRepo.save(appVersion);
    }
}

	
