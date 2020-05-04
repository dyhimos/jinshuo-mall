package com.jinshuo.mall.front.item.user;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.CommId;
import com.jinshuo.mall.service.item.application.qry.CategoryQry;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.service.command.SkuOptionComService;
import com.jinshuo.mall.service.item.service.query.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/7/23.
 */
@Slf4j
@RestController
@Api(description = "产品查询接口")
@RequestMapping("/v1/wx/spu")
public class SpuWxRestApi {

    @Autowired
    private SpuQueryService spuQueryService;

    @Autowired
    private SpuDescQueryService spuDescQueryService;

    @Autowired
    private TagQueryService tagQueryService;

    @Autowired
    private BrandQueryService brandQueryService;

    @Autowired
    private CategoryQueryService queryService;

    @Autowired
    private LatticeQueryService latticeQueryService;

    @Autowired
    private SkuOptionComService skuOptionComService;


    @PostMapping(value = "/getSpuByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "用户分页查询产品信息")
    public WrapperResponse get(@RequestBody SpuQry qry) {
        log.info(" -- 用户分页查询产品信息查询条件(getSpuByPage)：" + JSONObject.toJSONString(qry));
        return WrapperResponse.success(spuQueryService.queryPageInfo(qry));
    }


    @PostMapping(value = "/getRecommendProduct", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "获取推荐产品列表")
    public WrapperResponse getRecommendProduct(SpuQry qry) {
        return WrapperResponse.success(spuQueryService.getRecommendProduct(qry));
    }

    /*@PostMapping("/getExcellent")
    @ApiOperation(value = "查询优品产品")
    public WrapperResponse getExcellent(@RequestBody SpuQry qry) {
        return WrapperResponse.success(spuQueryService.getExcellent(qry));
    }*/

    /*@PostMapping("/getFeature")
    @ApiOperation(value = "查询爆款产品")
    public WrapperResponse getFeature(@RequestBody SpuQry qry) {
        return WrapperResponse.success(spuQueryService.getExcellent(qry));
    }*/


    @PostMapping(value = "/getSpuInfoBySpuId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "根据产品id查询产品详细信息")
    public WrapperResponse getByExemple(@RequestBody CommId qry) {
        return WrapperResponse.success(spuQueryService.findAllInfoBySpuId(qry.getId()));
    }

    @GetMapping("/getDescBySpuId/{spuId}")
    @ApiOperation(httpMethod = "GET", value = "获取产品图文详情")
    public WrapperResponse getBySpuId(@PathVariable(value = "spuId") Long spuId) {
        return WrapperResponse.success(spuDescQueryService.findBySpuId(spuId));
    }

    @GetMapping("/getTags")
    @ApiOperation(httpMethod = "GET", value = "获取查询标签集合")
    public WrapperResponse getTags() {
        return WrapperResponse.success(tagQueryService.getAll());
    }


    @GetMapping("/getBrands")
    @ApiOperation(httpMethod = "GET", value = "查询品牌集合")
    public WrapperResponse getBrands() {
        return WrapperResponse.success(brandQueryService.getAll());
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查询规格集合")
    public WrapperResponse<PageInfo> getByPage(@RequestBody CategoryQry qry) {
        return WrapperResponse.success(queryService.getCategorysByPage(qry));
    }


    @PostMapping("/getFirstPageCategorys")
    @ApiOperation(value = "查询首页类目集合")
    public WrapperResponse getFirstPageCategorys(@RequestBody CategoryQry qry) {
        return WrapperResponse.success(queryService.getFirstPageCategorys(qry));
    }


    @GetMapping("/getFirstLattice")
    @ApiOperation(value = "首页菜单格子查询")
    public WrapperResponse getFirstLattice() {
        return WrapperResponse.success(latticeQueryService.getFirstPageLattice());
    }


    /*@PostMapping("/queryDisProduct")
    @ApiOperation(httpMethod = "POST", value = "分页查询分销产品查询接口")
    public WrapperResponse create(@RequestBody SpuQry qry) {
        PageInfo pageInfo;
        try {
            pageInfo = spuQueryService.queryDisProduct(qry);
        } catch (Exception e) {
            log.error("系統错误,", e);
            return WrapperResponse.fail("网络异常！");
        }
        return WrapperResponse.success(pageInfo);
    }*/

    @PostMapping("/getByCategory")
    @ApiOperation(value = "根据类目查询产品")
    public WrapperResponse getByCategory(@RequestBody SpuQry qry) {
        return WrapperResponse.success(spuQueryService.getByCategory(qry));
    }

    @GetMapping("/getSpecBySpuId/{spuId}")
    @ApiOperation(httpMethod = "GET", value = "获取产品规格")
    public WrapperResponse getSpecBySpuId(@PathVariable(value = "spuId") Long spuId) {
        return WrapperResponse.success(skuOptionComService.getBySpuId(spuId));
    }


    @PostMapping("/testQueryProduct")
    @ApiOperation(value = "测试查询产品")
    public WrapperResponse testQueryProduct(@RequestBody SpuQry qry) {
        log.info(" -- 测试查询产品，输入参数：" + JSONObject.toJSONString(qry));
        PageInfo pageInfo = spuQueryService.queryPageInfoNew(qry);
        return WrapperResponse.success(pageInfo);
    }

    @PostMapping(value = "/getDtoSpuById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "根据产品skuId 或spuId 查询产品")
    public WrapperResponse getDtoSpuById(@RequestBody SpuQry qry) {
        log.info(" -- 用户分页查询产品信息查询条件：" + JSONObject.toJSONString(qry));
        return WrapperResponse.success(spuQueryService.getDtoSpuById(qry));
    }
}
