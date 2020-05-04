package com.jinshuo.mall.front.item.user;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.CouponLogsCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.CouponReceiveCreateCmd;
import com.jinshuo.mall.service.item.application.qry.CouponQry;
import com.jinshuo.mall.service.item.application.qry.CouponReceiveQry;
import com.jinshuo.mall.service.item.application.qry.OrderCouponQry;
import com.jinshuo.mall.service.item.service.command.CouponLogsComService;
import com.jinshuo.mall.service.item.service.command.CouponReceiveComService;
import com.jinshuo.mall.service.item.service.query.CouponQueryService;
import com.jinshuo.mall.service.item.service.query.CouponReceiveQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/7/31.
 */
@Slf4j
@RestController
@Api(description = "优惠券接口")
@RequestMapping("itemApi/v1/wx/coupon")
public class CouponWxRestApi {

    @Autowired
    private CouponReceiveQueryService couponReceiveQueryService;

    @Autowired
    private CouponReceiveComService couponReceiveComService;

    @Autowired
    private CouponQueryService couponQueryService;

    @Autowired
    private CouponLogsComService couponLogsComService;

    @ApiOperation(httpMethod = "POST", value = "获取待领取优惠券列表")
    @PostMapping(value = "/getCoupons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getCoupons(@RequestBody CouponQry qry) {
        try {
            return WrapperResponse.success(couponQueryService.getUserDtoByPage(qry));
        } catch (IcBizException e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系統错误,",e);
        }
        return WrapperResponse.fail("网络异常请稍后再试！");
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id查询优惠券详情")
    public WrapperResponse getById(@PathVariable(value = "id") Long id) {
        try {
            return WrapperResponse.success(couponQueryService.getUserDtoById(id));
        } catch (IcBizException e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系統错误,",e);
        }
        return WrapperResponse.fail("网络异常请稍后再试！");
    }

    @ApiOperation(httpMethod = "POST", value = "领取优惠券")
    @PostMapping(value = "/receiveCoupon", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse get(@RequestBody CouponReceiveCreateCmd cmd) {
        try {
            couponReceiveComService.create(cmd);
        } catch (IcBizException e) {
            log.error("领取优惠券报错",e);
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("领取优惠券报错",e);
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getMessage());
        }
        return WrapperResponse.success();
    }

    @ApiOperation(httpMethod = "POST", value = "分页查询我的优惠券")
    @PostMapping(value = "/getMyCoupons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getCoupons(@RequestBody CouponReceiveQry qry) {
        log.info(" -- 分页查询我的优惠券,输入参数：" + JSONObject.toJSONString(qry));
        try {
            return WrapperResponse.success(couponReceiveQueryService.getByPage(qry));
        } catch (IcBizException e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系統错误,",e);
        }
        return WrapperResponse.fail("网络异常请稍后再试！");
    }

    /*@ApiOperation(httpMethod = "GET", value = "查询我的未使用优惠券数量")
    @GetMapping(value = "/getMyCouponsCount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getMyCouponsCount() {
        try {
            return WrapperResponse.success(couponReceiveQueryService.getMyCouponsCount());
        } catch (IcBizException e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系統错误,",e);
        }
        return WrapperResponse.fail("网络异常请稍后再试！");
    }*/

    /*@ApiOperation(httpMethod = "POST", value = "分页查询我使用优惠券记录")
    @PostMapping(value = "/getMyUseCoupons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getMyUseCoupons(@RequestBody CouponReceiveQry qry) {
        return WrapperResponse.success(couponReceiveQueryService.getByPage(qry));
    }*/

    @ApiOperation(httpMethod = "POST", value = "校验优惠券是否可用")
    @PostMapping(value = "/checkCoupon", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse checkCoupon(@RequestBody CouponLogsCreateCmd cmd) {
        log.info(" -- 校验优惠券是否可用,输入参数：" + JSONObject.toJSONString(cmd));
        try {
            return WrapperResponse.success(couponLogsComService.checkCoupon(cmd));
        } catch (IcBizException e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail("系统异常，请稍后再试！");
        }
    }


    @ApiOperation(httpMethod = "POST", value = "根据订单信息查询可用优惠券")
    @PostMapping(value = "/getMyCouponByTargetId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getMyCouponByTargetId(@RequestBody OrderCouponQry qry) {
        try {
            return WrapperResponse.success(couponReceiveQueryService.getByOrderInfo(qry));
        } catch (IcBizException e) {
            log.error("系統错误,",e);
            return WrapperResponse.fail(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系統错误,",e);
        }
        return WrapperResponse.fail("网络异常请稍后再试！");
    }

}
