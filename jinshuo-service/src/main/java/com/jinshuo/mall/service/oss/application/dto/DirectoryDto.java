package com.jinshuo.mall.service.oss.application.dto;

import lombok.Data;

/**
 * Created by 19458 on 2019/6/20.
 */
@Data
public class DirectoryDto {

    /**
     * id
     */
    private String id;
    /**
     * 文件目录描述
     */
    private String fileDirectoryDes;
    /**
     * 文件目录
     */
    private String directory;

    /**
     * 父文件目录
     */
    private String fatherFileDirectoryId;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 文件类型
     */
    private Long shopId;
}
