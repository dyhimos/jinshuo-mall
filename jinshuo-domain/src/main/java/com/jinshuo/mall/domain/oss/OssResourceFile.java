package com.jinshuo.mall.domain.oss;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

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
@Builder
@Getter
@Accessors(chain = true)
@ToString(callSuper = true)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class OssResourceFile extends IdentifiedEntity {

    private OssResourceFileId resourceFileId;

    /**
     * 会员id
     */
    private Long userId;

    /**
     * 业务类型 10001后台产品图片 10002 客户评论图片
     */
    private Integer businessCode;

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
    private Long fileDirectoryId;

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
     * shopId
     */
    private Long shopId;

    public OssResourceFile insert() {
        super.preInsert();
        this.createDate = new Date();
        this.updateDate = new Date();
        this.remarks = "--";
        return this;
    }

    public OssResourceFile buildQuerySql(String fileDirectoryId, Integer fileType, String fileName, Long shopId) {
        this.shopId = shopId;
        if (StringUtils.isNotBlank(fileDirectoryId)) {
            this.fileDirectoryId = Long.parseLong(fileDirectoryId);
        }
        if (null != fileType) {
            this.fileType = fileType;
        }
        if (StringUtils.isNotBlank(fileName)) {
            this.fileName = fileName;
        }
        return this;
    }

}
