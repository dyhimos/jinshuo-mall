package com.jinshuo.mall.service.oss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.constant.FileDirectoryConstant;
import com.jinshuo.core.exception.oss.OssBizException;
import com.jinshuo.core.exception.oss.OssReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.utils.PicUtils;
import com.jinshuo.mall.domain.oss.OssResourceFile;
import com.jinshuo.mall.domain.oss.OssResourceFileId;
import com.jinshuo.mall.service.oss.application.assermbler.FileAssembler;
import com.jinshuo.mall.service.oss.application.dto.FileDto;
import com.jinshuo.mall.service.oss.application.dto.ResourceFileDto;
import com.jinshuo.mall.service.oss.mybatis.ResourceFileRepo;
import com.jinshuo.mall.service.social.service.com.SocialTopicComService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/6/19.
 */
@Slf4j
@Service
public class ResourceFileService {

    @Autowired
    private ResourceFileRepo resourceFileRepo;

    @Autowired
    private SocialTopicComService socialTopicComService;


    /**
     * 保存文件
     *
     * @return ResourceFile
     * @param1 multipartFile 文件
     * @param2 fileDirectoryId 目录id
     * @param3 fileType 文件类型
     */
    public OssResourceFile saveFile(MultipartFile multipartFile, Long fileDirectoryId, Integer fileType, Long shopId) throws IOException {
        //获取后缀名
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."), (multipartFile.getOriginalFilename().length()));
        //防止同一目录下文件太多
        String fileDir = "0";
        if (null != fileDirectoryId) {
            fileDir = fileDirectoryId.toString();
        }
        //获取高清文件存放目录
        String hdFilePath = FileDirectoryConstant.separator + FileDirectoryConstant.USERID + FileDirectoryConstant.separator + fileType +
                FileDirectoryConstant.separator + fileDir +
                FileDirectoryConstant.separator + FileDirectoryConstant.HD + FileDirectoryConstant.separator + UUID.randomUUID().toString() + suffix;
        File saveFile = new File(FileDirectoryConstant.FILEPATH + hdFilePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(multipartFile.getBytes());
            if (multipartFile.getSize() > 800 * 1024) {
                PicUtils.commpressPicForSize(FileDirectoryConstant.FILEPATH + hdFilePath, FileDirectoryConstant.FILEPATH + hdFilePath, 300, 0.9);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
        //获取压缩图片存放目录
        String coFilePath = "";
        try {
            coFilePath = FileDirectoryConstant.separator + FileDirectoryConstant.USERID + FileDirectoryConstant.separator + fileType +
                    FileDirectoryConstant.separator + fileDir +
                    FileDirectoryConstant.separator + FileDirectoryConstant.CO + FileDirectoryConstant.separator + UUID.randomUUID().toString() + suffix;
            PicUtils.commpressPicForSize(FileDirectoryConstant.FILEPATH + hdFilePath, FileDirectoryConstant.FILEPATH + coFilePath, 30, 0.9);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 记录数据库
        OssResourceFile resourceFile = OssResourceFile.builder()
                .resourceFileId(new OssResourceFileId(CommonSelfIdGenerator.generateId()))
                .fileDirectoryId(fileDirectoryId)
                .fileType(fileType)
                .userId(10088L) //默认商家
                .fileSize(multipartFile.getSize())
                .fileName(multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf(".")))
                .fileSuffix(suffix)
                .fileUrl(FileDirectoryConstant.URL + hdFilePath)
                .contourActDir(FileDirectoryConstant.FILEPATH2 + coFilePath)
                .actualDirectory(FileDirectoryConstant.FILEPATH2 + hdFilePath)
                .fileUrl(FileDirectoryConstant.URL + hdFilePath)
                .contourUrl(FileDirectoryConstant.URL + coFilePath)
                .shopId(shopId)
                .build();
        resourceFile.insert();
        resourceFileRepo.saveFile(resourceFile);
        return resourceFile;
    }

    public void updateFile(OssResourceFile resourceFile) {
    }

    public void deleteFile(String id) {
        String[] ids = new String[0];
        ids = id.split(",");
        if (FileDirectoryConstant.DELETEFLAG) {
            //ResourceFile resourceFile = resourceFileRepo.updateFile();
            try {
                for (int i = 0; i < ids.length; i++) {
                    OssResourceFile resourceFile = resourceFileRepo.findById(Long.parseLong(id));
                    File file1 = new File(resourceFile.getActualDirectory());
                    File file2 = new File(resourceFile.getContourActDir());
                    if (file1.exists()) {
                        file1.delete();
                    }
                    if (file2.exists()) {
                        file2.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resourceFileRepo.deleteFile(ids);
    }

    public PageInfo findByDicId(ResourceFileDto dto) {
        if (null == dto.getShopId()) {
            dto.setShopId(10088L);
        }
        OssResourceFile resourceFile1 = new OssResourceFile();
        resourceFile1.buildQuerySql(dto.getFileDirectoryId(), dto.getFileType(), dto.getFileName(), dto.getShopId());
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<OssResourceFile> list = resourceFileRepo.findByDicId(resourceFile1);
        List<FileDto> dtos = list.stream()
                .map(resourceFile -> FileAssembler.assembleResourceFileDto(resourceFile))
                .collect(Collectors.toList());
        PageInfo<FileDto> pageInfo = new PageInfo(list);
        pageInfo.setList(dtos);
        return pageInfo;
    }

    /**
     * 根据目录id删除文件
     *
     * @param id
     * @return
     */
    public int deleteByDicId(Long id) {
        List<OssResourceFile> resourceFiles = resourceFileRepo.findByDId(id);
        if (null == resourceFiles || resourceFiles.size() < 1) {
            return 0;
        }
        for (OssResourceFile resourceFile : resourceFiles) {
            File file1 = new File(resourceFile.getActualDirectory());
            File file2 = new File(resourceFile.getContourActDir());
            if (file1.exists()) {
                file1.delete();
            }
            if (file2.exists()) {
                file2.delete();
            }
        }
        resourceFileRepo.deleteByDicId(id);
        return resourceFiles.size();
    }


    /**
     * 保存文件
     *
     * @return ResourceFile
     * @param1 multipartFile 文件
     * @param2 fileDirectoryId 目录id
     * @param3 fileType 文件类型
     */
    public OssResourceFile saveFileCommon(MultipartHttpServletRequest multipartRequest) throws IOException {
        log.info(" -- 保存文件(saveFileCommon),输入参数，{}", multipartRequest.getParameterMap());
        Integer businessCode = null;
        Long fileDirectoryId = null;
        Integer fileType = null;
        Long shopId = null;
        MultipartFile multipartFile;
        try {
            businessCode = Integer.parseInt(multipartRequest.getParameter("businessCode"));
            fileDirectoryId = Long.parseLong((multipartRequest.getParameter("fileDirectoryId")));
            fileType = Integer.parseInt(multipartRequest.getParameter("fileType"));
            shopId = Long.parseLong(multipartRequest.getParameter("shopId"));
            multipartFile = multipartRequest.getFile("file");
        } catch (Exception e) {
            e.printStackTrace();
            throw new OssBizException(OssReturnCode.IC210001.getCode(), OssReturnCode.IC210001.getMsg());
        }
        if (null == businessCode || null == fileDirectoryId || null == fileType || null == shopId) {
            throw new OssBizException(OssReturnCode.IC210001.getCode(), OssReturnCode.IC210001.getMsg());
        }

        //获取后缀名
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."), (multipartFile.getOriginalFilename().length()));
        //防止同一目录下文件太多
        String fileDir = "0";
        if (null != fileDirectoryId) {
            fileDir = fileDirectoryId.toString();
        }
        //获取高清文件存放目录
        String hdFilePath = FileDirectoryConstant.separator + FileDirectoryConstant.USERID + FileDirectoryConstant.separator + shopId +
                FileDirectoryConstant.separator + businessCode + FileDirectoryConstant.separator + fileType + FileDirectoryConstant.separator + fileDir +
                FileDirectoryConstant.separator + FileDirectoryConstant.HD + FileDirectoryConstant.separator + UUID.randomUUID().toString() + suffix;
        File saveFile = new File(FileDirectoryConstant.FILEPATH + hdFilePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(multipartFile.getBytes());
            if (multipartFile.getSize() > 800 * 1024) {
                PicUtils.commpressPicForSize(FileDirectoryConstant.FILEPATH + hdFilePath, FileDirectoryConstant.FILEPATH + hdFilePath, 300, 0.9);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
        //获取压缩图片存放目录
        String coFilePath = "";
        try {
            coFilePath = FileDirectoryConstant.separator + FileDirectoryConstant.USERID + FileDirectoryConstant.separator + shopId +
                    FileDirectoryConstant.separator + businessCode + FileDirectoryConstant.separator + fileType +
                    FileDirectoryConstant.separator + fileDir + FileDirectoryConstant.separator + FileDirectoryConstant.CO +
                    FileDirectoryConstant.separator + UUID.randomUUID().toString() + suffix;
            PicUtils.commpressPicForSize(FileDirectoryConstant.FILEPATH + hdFilePath, FileDirectoryConstant.FILEPATH + coFilePath, 30, 0.9);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 记录数据库
        OssResourceFile resourceFile = OssResourceFile.builder()
                .businessCode(businessCode)
                .resourceFileId(new OssResourceFileId(CommonSelfIdGenerator.generateId()))
                .fileDirectoryId(fileDirectoryId)
                .fileType(fileType)
                .userId(Long.parseLong(FileDirectoryConstant.USERID)) //默认商家
                .fileSize(multipartFile.getSize())
                .fileName(multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf(".")))
                .fileSuffix(suffix)
                .fileUrl(FileDirectoryConstant.URL + hdFilePath)
                .contourActDir(FileDirectoryConstant.FILEPATH2 + coFilePath)
                .actualDirectory(FileDirectoryConstant.FILEPATH2 + hdFilePath)
                .fileUrl(FileDirectoryConstant.URL + hdFilePath)
                .contourUrl(FileDirectoryConstant.URL + coFilePath)
                .shopId(shopId)
                .build();
        resourceFile.insert();
        resourceFileRepo.saveFile(resourceFile);
        /*if (OssBusinessCodeEnums.TOPICPICTURE.code.equals(businessCode)) {
            Long topicId = Long.parseLong(multipartRequest.getParameter("topicId"));
            Integer sort = Integer.parseInt(multipartRequest.getParameter("sort"));
            SocialTopicAlbumCmd cmd = SocialTopicAlbumCmd.builder()
                    .photoUrl(resourceFile.getFileUrl())
                    .topicId(topicId)
                    .sort(sort)
                    .build();
            socialTopicComService.saveAlbum(cmd);
        }*/
        return resourceFile;
    }
}
