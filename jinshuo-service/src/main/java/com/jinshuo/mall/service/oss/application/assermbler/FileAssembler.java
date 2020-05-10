package com.jinshuo.mall.service.oss.application.assermbler;

import com.jinshuo.core.utils.UrlUtil;
import com.jinshuo.mall.domain.oss.OssResourceFile;
import com.jinshuo.mall.service.oss.application.dto.FileDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class FileAssembler {

    /**
     * @param resourceFile
     * @return
     */
    public static FileDto assembleResourceFileDto(OssResourceFile resourceFile) {
        if (resourceFile == null) {
            return null;
        }
        FileDto dto = new FileDto();
        BeanUtils.copyProperties(resourceFile, dto);
        dto.setHdRelativelyUrl(UrlUtil.getRelativelyUrl(dto.getFileUrl()));
        dto.setCoRelativelyUrl(UrlUtil.getRelativelyUrl(dto.getContourUrl()));
        dto.setId(resourceFile.getResourceFileId().getId().toString());
        dto.setUserId(resourceFile.getUserId().toString());
        return dto;
    }
}
