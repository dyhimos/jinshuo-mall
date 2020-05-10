package com.jinshuo.mall.service.oss.mybatis;

import com.jinshuo.mall.domain.oss.OssFileDirectory;
import com.jinshuo.mall.service.oss.application.dto.DirectoryDto;
import com.jinshuo.mall.service.oss.mybatis.mapper.FileDirectoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 目录管理
 * @Author: dyh
 * @CreateDate: 2019/6/19 17:50
 * @UpdateUser: dyh
 * @UpdateDate: 2019/6/19 17:50
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
@Repository
public class FileDirectoryRepo  {

    @Autowired
    private FileDirectoryMapper fileDirectoryMapper;

    public void createDirecfctory(OssFileDirectory dto) {
        fileDirectoryMapper.create(dto);
    }

    public void updateDirecfctory(OssFileDirectory dto) {
        fileDirectoryMapper.updateDirecfctory(dto);
    }

    public void deleteDirecfctory(String id) {
        fileDirectoryMapper.deleteDirecfctory(id);
    }

    public List<OssFileDirectory> queryDirectoryTree(DirectoryDto dto) {
        String userId = "10088";//公共方法获取userid
        Long faId = null;
        if (StringUtils.isNotBlank(dto.getFatherFileDirectoryId())) {
            faId = Long.parseLong(dto.getFatherFileDirectoryId());
        }
        try {
            return fileDirectoryMapper.selectByDto(dto.getShopId(),userId, faId, dto.getFileType());
        } catch (Exception e) {
            log.error(" -- cuol2");
            e.printStackTrace();
        }
        return null;
    }

    public OssFileDirectory findById(Long id) {
        return fileDirectoryMapper.selectById(id);
    }
}
