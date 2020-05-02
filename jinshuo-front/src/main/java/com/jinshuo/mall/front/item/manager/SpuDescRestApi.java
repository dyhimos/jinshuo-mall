package com.jinshuo.mall.front.item.manager;

import com.alibaba.fastjson.JSONException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.SpuDescCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuDescUpdateCmd;
import com.jinshuo.mall.service.item.service.command.SpuDescComService;
import com.jinshuo.mall.service.item.service.query.SpuDescQueryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 19458 on 2019/7/18.
 */
@Slf4j
@RestController
@Api(description = "产品详细说明管理接口")
@RequestMapping("/v1/manager/spuDesc")
public class SpuDescRestApi {

    @Autowired
    private SpuDescComService spuDescComService;

    @Autowired
    private SpuDescQueryService spuDescQueryService;

    @PostMapping("/create")
    public WrapperResponse create(@Valid @RequestBody SpuDescCreateCmd cmd) {
        spuDescComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    public WrapperResponse update(@Valid @RequestBody SpuDescUpdateCmd cmd) {
        spuDescComService.update(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/getBySpuId/{spuId}")
    public WrapperResponse getBySpuId(@PathVariable(value = "spuId") Long spuId) {
        return WrapperResponse.success(spuDescQueryService.findBySpuId(spuId));
    }



    @RequestMapping("/controller")
    @ResponseBody
    public void getConfigInfo(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext()
                .getRealPath("/");
        try {
            //String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            //writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException | JSONException e) {
            log.error("系統错误,",e);
        }
    }
}
