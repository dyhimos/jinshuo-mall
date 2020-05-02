package com.jinshuo.mall.domain.user.model.appVersion;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * app版本管理
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppVersion extends IdentifiedEntity {
    private AppVersionId appVersionId;
    /**
     * 安卓下载地址
     */
    private String androidDownloadAddress;
    /**
     * 苹果下载地址
     */
    private String iosDownloadAddress;

    /**
     * 版本号
     */
    private Integer appVersion;


}

