package com.jinshuo.mall.service.oss.service;

import com.jinshuo.core.constant.FileDirectoryConstant;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.oss.OssFileDirectory;
import com.jinshuo.mall.domain.oss.OssFileDirectoryId;
import com.jinshuo.mall.service.oss.application.assermbler.FileDirectoryAssembler;
import com.jinshuo.mall.service.oss.application.dto.DirectoryDto;
import com.jinshuo.mall.service.oss.application.dto.FileDirectoryDto;
import com.jinshuo.mall.service.oss.mybatis.FileDirectoryRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/6/20.
 */
@Service
public class FileDirectoryService {

    @Autowired
    private FileDirectoryRepo repo;

    @Autowired
    private ResourceFileService resourceFileService;


    /**
     * 创建目录
     *
     * @param dto
     * @return
     */
    public void createDire(DirectoryDto dto) {
        if (null == dto.getShopId()) {
            dto.setShopId(10088l);
        }
        OssFileDirectory fileDirectory = OssFileDirectory.builder()
                .fileDirectoryId(new OssFileDirectoryId(CommonSelfIdGenerator.generateId()))
                .directory(dto.getDirectory())
                .fileType(dto.getFileType())
                .shopId(dto.getShopId())
                .build();
        if (StringUtils.isNotBlank(dto.getFatherFileDirectoryId())) {
            fileDirectory.setFatherFileDirectoryId(new OssFileDirectoryId(Long.parseLong(dto.getFatherFileDirectoryId())));
        }
        fileDirectory.insert();
        repo.createDirecfctory(fileDirectory);
    }

    /**
     * 修改目录
     *
     * @param dto
     * @return
     */
    public void updateDire(DirectoryDto dto) {
        OssFileDirectory fileDirectory = OssFileDirectory.builder()
                .fileDirectoryId(new OssFileDirectoryId(Long.parseLong(dto.getId())))
                .directory(dto.getDirectory())
                .fileType(dto.getFileType())
                .fatherFileDirectoryId(new OssFileDirectoryId(Long.parseLong(dto.getFatherFileDirectoryId())))
                .userId(10088l)
                .build();
        repo.updateDirecfctory(fileDirectory);
    }

    /**
     * 删除目录
     *
     * @param id
     * @return
     */
    public void deleteDire(String id) {
        if (FileDirectoryConstant.DELETEFLAG) {
            OssFileDirectory fileDirectory = repo.findById(Long.parseLong(id));
            if (null == fileDirectory) {
                return;
            }
            boolean flag;
            try {
                resourceFileService.deleteByDicId(Long.parseLong(id));
                String filePath = FileDirectoryConstant.FILEPATH2 + FileDirectoryConstant.separator + FileDirectoryConstant.USERID + FileDirectoryConstant.separator + fileDirectory.getFileType() +
                        FileDirectoryConstant.separator + fileDirectory.getFileDirectoryId().getId() + FileDirectoryConstant.separator;
                File file1 = new File(filePath + FileDirectoryConstant.HD);
                File file2 = new File(filePath + FileDirectoryConstant.CO);
                File file3 = new File(filePath);
                if (file1.exists()) {
                    file1.delete();
                }
                if (file2.exists()) {
                    file2.delete();
                }
                if (file3.exists()) {
                    file3.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        repo.deleteDirecfctory(id);
    }

    /**
     * 查询目录
     *
     * @param dto
     * @return
     */
    public List<FileDirectoryDto> queryDireTree(DirectoryDto dto) {
        List<OssFileDirectory> list = repo.queryDirectoryTree(dto);
        List<FileDirectoryDto> dtos = list.stream()
                .map(fileDirectory -> FileDirectoryAssembler.assembleFileDirectoryDto(fileDirectory))
                .collect(Collectors.toList());
        return dtos;
    }

}
