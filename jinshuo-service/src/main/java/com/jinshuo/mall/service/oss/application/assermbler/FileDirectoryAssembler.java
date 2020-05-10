package com.jinshuo.mall.service.oss.application.assermbler;

import com.jinshuo.mall.domain.oss.OssFileDirectory;
import com.jinshuo.mall.service.oss.application.dto.FileDirectoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Created by dyh on 2019/8/28.
 */
@Slf4j
public class FileDirectoryAssembler {

    /**
     * 转换为前端展示对象
     *
     * @param fileDirectory
     * @return
     */
    public static FileDirectoryDto assembleFileDirectoryDto(OssFileDirectory fileDirectory) {
        if (null == fileDirectory) {
            return null;
        }
        FileDirectoryDto dto = new FileDirectoryDto();
        BeanUtils.copyProperties(fileDirectory, dto);
        dto.setId(fileDirectory.getFileDirectoryId().getId().toString());
        try {
            dto.setFatherFileDirectoryId(fileDirectory.getFatherFileDirectoryId().getId().toString());
        } catch (Exception e) {
            log.info(" -- 没有父id此处不打印错误");
            //e.printStackTrace();
        }
        return dto;
    }
}
