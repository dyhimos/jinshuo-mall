package com.jinshuo.mall.service.oss.application.dto;

import lombok.Data;

/**
 * Created by 19458 on 2019/8/28.
 */
@Data
public class FileDirectoryDto {

    /**
     * ID
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文件目录描述
     */
    private String fileDirectoryDes;

    /**
     * 文件目录
     */
    private String directory;

    /**
     * 目录类型
     */
    private Integer fileType;

    /**
     * 父文件目录
     */
    private String fatherFileDirectoryId;
}
