package com.jinshuo.mall.front.out;

import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.order.application.cmd.GoodsOrderPayCmd;
import com.jinshuo.mall.service.order.service.command.GoodsOrderCmdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2020/5/4.
 */
@Component
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/wx/")
@Api(description = "微信订单接口")
@AllArgsConstructor
public class WxController {

    @Autowired
    private GoodsOrderCmdService goodsOrderCmdService;

    @PostMapping("/pay/wechat")
    @ApiOperation("微信支付订单")
    public WrapperResponse wechat(@Validated @RequestBody GoodsOrderPayCmd goodsOrderPayCmd) throws OcBizException {
        return null;
        //return WrapperResponse.success(goodsOrderCmdService.wechat(goodsOrderPayCmd));
    }

    /**
     * 微信回调
     *
     * @param request
     * @throws Exception
     */
    @PostMapping(value = "/notify")
    public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return;
        //goodsOrderCmdService.weixin_notify(request, response);
    }
}
