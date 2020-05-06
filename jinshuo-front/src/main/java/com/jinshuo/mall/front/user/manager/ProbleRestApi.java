package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.ProblemCmd;
import com.jinshuo.mall.service.user.application.qry.ProblemQry;
import com.jinshuo.mall.service.user.service.command.ProblemComService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2020/4/12.
 */
@Slf4j
@RestController
@Api(description = "常见问题")
@RequestMapping("/v1/manager/problem")
public class ProbleRestApi {

    @Autowired
    private ProblemComService problemComService;


    /**
     * 保存常见问题
     *
     * @param cmd
     * @return
     */
    @PostMapping("/saveProblem")
    @ApiOperation("保存常见问题")
    public WrapperResponse save(@RequestBody ProblemCmd cmd) {
        problemComService.save(cmd);
        return WrapperResponse.success();
    }


    @GetMapping("/delete/{id}")
    @ApiOperation("删除常见问题")
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(problemComService.delete(id));
    }

    /**
     * 查询常见问题
     *
     * @param cmd
     * @return
     */
    @PostMapping("/queryProblems")
    @ApiOperation("查询常见问题")
    public WrapperResponse queryProblems(@RequestBody ProblemQry cmd) {
        return WrapperResponse.success(problemComService.queryProblems(cmd));
    }


    @GetMapping("/queryProblemDetail/{id}")
    @ApiOperation("查询常见问题详情")
    public WrapperResponse queryProblemDetail(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(problemComService.queryDetail(id));
    }
}
