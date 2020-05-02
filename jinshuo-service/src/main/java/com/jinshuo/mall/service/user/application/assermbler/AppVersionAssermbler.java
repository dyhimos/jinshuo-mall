package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.appVersion.AppVersion;
import com.jinshuo.mall.service.user.application.dto.AppVersionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class AppVersionAssermbler {

    /**
     * 转化为列表dto
     *
     * @param appVersion
     * @return
     */
    public static AppVersionDto appVersionDto(AppVersion appVersion) {
        if (null == appVersion) {
            return null;
        }
        AppVersionDto appVersionDto = new AppVersionDto();
        BeanUtils.copyProperties(appVersion, appVersionDto);
        appVersionDto.setId(appVersion.getAppVersionId().getId());
        return appVersionDto;
    }
}

	
