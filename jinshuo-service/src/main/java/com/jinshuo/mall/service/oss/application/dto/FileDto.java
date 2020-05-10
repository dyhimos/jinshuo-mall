package com.jinshuo.mall.service.oss.application.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 文件目录
 * @Author: dyh
 * @CreateDate: 2019/6/19 15:50
 * @UpdateUser: dyh
 * @UpdateDate: 2019/6/19 15:50
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
public class FileDto {
    /**
     * 会员id
     */
    private String id;
    /**
     * 会员id
     */
    @NotNull(message = "用户id不能为空！")
    private String userId;
    /**
     * 会员类型
     */
    private String userType;

    /**
     * 文件类型 1:图片 2:视频 3：文档 4：其他文件
     */
    private Integer fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 实
     * 录
     */
    private String actualDirectory;

    /**
     * 文件目录
     */
    private String fileDirectoryId;

    /**
     * 资源路径
     */
    private String fileUrl;

    /**
     * 缩略图资源路径
     */
    private String contourUrl;

    /**
     * 缩略图实际存放地址
     */
    private String contourActDir;

    /**
     * 相对高清地址
     */
    private String hdRelativelyUrl;

    /**
     * 相对压缩地址
     */
    private String coRelativelyUrl;

}
