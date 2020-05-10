package com.jinshuo.mall.service.oss.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class ResourceFileDto {

    @ApiModelProperty(value = "页码")
    public Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    public Integer pageSize = 10;

    /**
     * 会员id
     */
    private String userId;

    /**
     * shopId
     */
    private Long shopId;

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
     * 实际目录
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
     * 资源路径
     */
    private String ids;
}
