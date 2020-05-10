package com.jinshuo.mall.front.oss;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.oss.OssResourceFile;
import com.jinshuo.mall.service.oss.application.dto.ResourceFileDto;
import com.jinshuo.mall.service.oss.service.ResourceFileService;
import com.jinshuo.mall.service.social.service.com.SocialTopicComService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;


/**
 * @Description: 资源上传API
 * @Author: dyh
 * @CreateDate: 2019/6/19 17:50
 * @UpdateUser: dyh
 * @UpdateDate: 2019/6/19 17:50
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/file")
@Api(value = "REST-OssFileApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@SuppressWarnings({"rawtypes", "unchecked"})
public class OssFileApi {

    @Autowired
    private ResourceFileService resourceFileService;

    @Autowired
    private SocialTopicComService socialTopicComService;


    @ApiOperation("上传资源文件")
    @PostMapping("/upload")
    @ResponseBody
    public WrapperResponse upload(MultipartHttpServletRequest multipartRequest) throws IOException {
        log.info(" -- 开始上传资源文件，输入参数：" + JSONObject.toJSONString(multipartRequest.getParameterMap()));
        OssResourceFile resourceFile;
        try {
            String fileDirectoryIdStr = (multipartRequest.getParameter("fileDirectoryId"));
            String fileTypeStr = multipartRequest.getParameter("fileType");
            String shopIdTemp = multipartRequest.getParameter("shopId");
            Long fileDirectoryId = null;
            Integer fileType = 1;
            Long shopId = Long.parseLong(shopIdTemp);
            if (StringUtils.isNotBlank(fileDirectoryIdStr)) {
                fileDirectoryId = Long.parseLong(fileDirectoryIdStr);
            }
            if (StringUtils.isNotBlank(fileTypeStr)) {
                fileType = Integer.parseInt(fileTypeStr);
            }
            resourceFile = resourceFileService.saveFile(multipartRequest.getFile("file"), fileDirectoryId, fileType, shopId);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapperResponse.fail(-1, "网络异常，请稍后再试！");
        }
        log.info(" -- 上传资源文件完成，保存参数：" + JSONObject.toJSONString(resourceFile));
        return WrapperResponse.success(resourceFile);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除资源")
    public WrapperResponse delete(@RequestBody ResourceFileDto dto) {
        resourceFileService.deleteFile(dto.getIds());
        return WrapperResponse.success();
    }

    @PostMapping("/queryFile")
    @ApiOperation(value = "查询资源")
    public WrapperResponse queryFile(@RequestBody ResourceFileDto dto) {
        System.out.println("----- 查询资源,输入参数：" + JSONObject.toJSONString(dto));
        return WrapperResponse.success(resourceFileService.findByDicId(dto));
    }


    @ApiOperation("公共上传资源文件")
    @PostMapping("/commonUpload")
    @ResponseBody
    public WrapperResponse commonUpload(MultipartHttpServletRequest multipartRequest) throws IOException {
        log.info(" -- 开始公共上传资源文件，输入参数：" + JSONObject.toJSONString(multipartRequest.getParameterMap()));
        OssResourceFile resourceFile;
        try {
            resourceFile = resourceFileService.saveFileCommon(multipartRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return WrapperResponse.fail(-1, "网络异常，请稍后再试！");
        }
        log.info(" -- 上传资源文件完成，保存参数：" + JSONObject.toJSONString(resourceFile));
        return WrapperResponse.success(resourceFile);
    }

}
