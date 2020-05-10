package com.jinshuo.mall.front.oss;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.oss.application.dto.DirectoryDto;
import com.jinshuo.mall.service.oss.service.FileDirectoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
@RestController
@RequestMapping(value = "/v1/dire")
@Api(value = "REST-OssDirectoryApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@SuppressWarnings({"rawtypes", "unchecked"})
public class OssDirectoryApi {

    @Autowired
    private FileDirectoryService fileDirectoryService;


    @PostMapping("/create")
    @ApiOperation(value = "创建目录")
    public WrapperResponse create(@RequestBody DirectoryDto dto) {
        log.info(" -- 开始创建目录，输入参数：" + JSONObject.toJSONString(dto));
        fileDirectoryService.createDire(dto);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改目录")
    public WrapperResponse update(@RequestBody DirectoryDto dto) {
        fileDirectoryService.updateDire(dto);
        return WrapperResponse.success();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除目录")
    public WrapperResponse delete(@PathVariable(value = "id") String id) {
        fileDirectoryService.deleteDire(id);
        return WrapperResponse.success();
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询目录")
    public WrapperResponse query(@RequestBody DirectoryDto dto) {
        log.info(" -- 开始查询目录，输入参数：" + JSONObject.toJSONString(dto));
        return WrapperResponse.success(fileDirectoryService.queryDireTree(dto));
    }
}
