package com.jinshuo.mall.domain.oss;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 文件目录类型
 * @Author: dyh
 * @CreateDate: 2019/6/19 15:50
 * @UpdateUser: dyh
 * @UpdateDate: 2019/6/19 15:50
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OssFileDirectory extends IdentifiedEntity {

    private OssFileDirectoryId fileDirectoryId;

    /**
     * 用户id
     */
    private Long userId;

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
     * 店铺名称
     */
    private Long shopId;

    /**
     * 父文件目录
     */
    private OssFileDirectoryId fatherFileDirectoryId;

    public OssFileDirectory insert() {
        super.preInsert();
        return this;
    }
}
