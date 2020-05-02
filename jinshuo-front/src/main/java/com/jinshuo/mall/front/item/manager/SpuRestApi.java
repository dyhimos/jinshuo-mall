package com.jinshuo.mall.front.item.manager;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.CommId;
import com.jinshuo.mall.service.item.application.cmd.SpuUpdateCmd;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.service.command.SkuComService;
import com.jinshuo.mall.service.item.service.command.SpuComService;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Classname SpuRestApi
 * @Description TODO
 * @Date 2019/6/16 19:52
 * @Created by dyh
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/manager/spu")
@Api(description = "产品管理接口")
@AllArgsConstructor
public class SpuRestApi {

    @Autowired
    private SpuQueryService spuQueryService;

    @Autowired
    private SpuComService spuComService;

    @Autowired
    private SkuComService skuComService;


    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "添加产品")
    public WrapperResponse create(@Valid @RequestBody SpuUpdateCmd cmd) {
        log.info("---添加产品--- 输入参数：" + JSONObject.toJSONString(cmd));
        String spuId = null;
        try {
            spuId = spuComService.save(cmd).getSpuId().getId().toString();
        } catch (IcBizException e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getMessage());
        }
        return WrapperResponse.success(spuId);
    }


    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改产品")
    public WrapperResponse update(@Valid @RequestBody SpuUpdateCmd cmd) {
        log.info("---修改产品--- 输入参数：" + JSONObject.toJSONString(cmd));
        spuComService.update(cmd);
        return WrapperResponse.success();
    }


    @PostMapping(value = "/getSpuByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "分页查询产品信息")
    public WrapperResponse get(@RequestBody SpuQry qry) {
        return WrapperResponse.success(spuQueryService.getPageInfo(qry));
    }

    @PostMapping(value = "/getSpuBySpuId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "根据spuid查询产品信息")
    public WrapperResponse getByExemple(@RequestBody CommId qry) {
        return WrapperResponse.success(spuQueryService.findBySpuId(qry.getId()));
    }

    @GetMapping("/delete/{spuId}")
    @ApiOperation(value = "删除商品")
    public WrapperResponse delete(@PathVariable(value = "spuId") Long spuId) {
        return WrapperResponse.success(spuComService.deleteBySpuId(spuId));
    }

    @GetMapping("/upProduct/{spuId}/{goodsStatus}")
    @ApiOperation(value = "上下架产品")
    public WrapperResponse upProduct(@PathVariable(value = "spuId") Long spuId, @PathVariable(value = "goodsStatus") Integer goodsStatus) {
        Integer count = 0;
        try {
            count = spuComService.upProduct(spuId, goodsStatus);
            if (1 != count) {
                return WrapperResponse.fail("更新产品状态失败！");
            }
        } catch (Exception e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(IcReturnCode.IC200003.getCode(), IcReturnCode.IC200003.getMsg());
        }
        return WrapperResponse.success(count);
    }

    @GetMapping("/updateSort/{spuId}/{sort}")
    @ApiOperation(value = "修改产品排序")
    public WrapperResponse updateSort(@PathVariable(value = "spuId") Long spuId, @PathVariable(value = "sort") Integer sort) {
        Integer count = 0;
        try {
            count = spuComService.updateSort(spuId, sort);
            if (1 != count) {
                return WrapperResponse.fail(-1,"更新产品状态失败！");
            }
        } catch (Exception e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(IcReturnCode.IC200003.getCode(), IcReturnCode.IC200003.getMsg());
        }
        return WrapperResponse.success(count);
    }


    @GetMapping("/deleteSkuById/{skuId}")
    @ApiOperation(value = "删除商品sku ")
    public WrapperResponse deleteSkuById(@PathVariable(value = "skuId") Long skuId) {
        skuComService.deleteSku(skuId);
        return WrapperResponse.success();
    }



}

