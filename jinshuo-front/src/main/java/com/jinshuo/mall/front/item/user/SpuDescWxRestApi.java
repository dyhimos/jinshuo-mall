package com.jinshuo.mall.front.item.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.SpuDescCreateCmd;
import com.jinshuo.mall.service.item.service.command.SpuDescComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/18.
 */
@RestController
@RequestMapping("/v1/wx/specDesc")
public class SpuDescWxRestApi {

    @Autowired
    private SpuDescComService spuDescComService;

    @PostMapping("/create")
    public WrapperResponse create(@Valid @RequestBody SpuDescCreateCmd cmd) {
        spuDescComService.create(cmd);
        return WrapperResponse.success();
    }
}
