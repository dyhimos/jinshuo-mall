package com.jinshuo.mall.front.out;

import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.order.application.cmd.GoodsOrderPayCmd;
import com.jinshuo.mall.service.order.service.command.GoodsOrderCmdService;
import com.jinshuo.mall.service.user.application.cmd.BatchgetMaterialCmd;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.application.cmd.WxCreateQrcodeCmd;
import com.jinshuo.mall.service.user.service.command.WxMenuService;
import com.jinshuo.mall.service.wx.application.service.WxConfigCmdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2020/5/4.
 */
@Component
@Slf4j
@RestController
@Validated
@RequestMapping("/jinShuoApi/v1/wx/")
@Api(description = "微信订单接口")
@AllArgsConstructor
public class WxController {

    @Autowired
    private GoodsOrderCmdService goodsOrderCmdService;

    @Autowired
    private WxConfigCmdService wxConfigCmdService;

    @Autowired
    private WxMenuService wxMenuService;

    /**
     * 微信回调信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("public/callBack")
    @ApiOperation("微信回调 001 接口")
    public String callBackGet(@RequestBody(required = false) String body, String signature, String timestamp, String nonce, String echostr) throws Exception {
        return wxConfigCmdService.callBackGet(body, signature, timestamp, nonce, echostr);
    }


    @PostMapping("public/getConfig")
    @ApiOperation("获取配置信息 接口")
    public WrapperResponse getConfig(@Validated @RequestBody ShopIdCmd shopIdCmd) {
        return WrapperResponse.success(wxConfigCmdService.getWxConfig(shopIdCmd));
    }

    @PostMapping("public/pay/wechat")
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
    @PostMapping(value = "public/notify")
    public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return;
        //goodsOrderCmdService.weixin_notify(request, response);
    }

    @PostMapping("public/getToken")
    @ApiOperation("获取token信息")
    public WrapperResponse getToken(@Validated @RequestBody ShopIdCmd shopIdCmd) throws Exception {
        return WrapperResponse.success(wxConfigCmdService.getToken(shopIdCmd));
    }




    /**
     * 微信回调信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("public/callBack")
    public String callBackPost(@RequestBody(required = false) String body, String signature, String timestamp, String nonce, String echostr) throws Exception {
        return wxConfigCmdService.callBackPost(body, signature, timestamp, nonce, echostr);
    }

    /**
     * 创建二维码
     *
     * @return
     */
    @ApiOperation("创建二维码")
    @PostMapping("public/createQrcode")
    public WrapperResponse createQrcode(@RequestBody WxCreateQrcodeCmd wxCreateQrcodeCmd) throws Exception {
        return WrapperResponse.success(wxMenuService.createQrcode(wxCreateQrcodeCmd));
    }

    /**
     * 创建微信菜单
     *
     * @param shopIdCmd
     * @return
     * @throws Exception
     */
    @ApiOperation("创建微信菜单")
    @PostMapping("public/createMenu")
    public WrapperResponse createMenu(@RequestBody ShopIdCmd shopIdCmd) throws Exception {
        wxMenuService.createMenu(shopIdCmd);
        return WrapperResponse.success();
    }

    /**
     * 获取微信素材列表
     *
     * @return
     */
    @PostMapping("public/batchgetMaterial")
    public WrapperResponse batchgetMaterial(@RequestBody BatchgetMaterialCmd batchgetMaterialCmd) throws Exception {
        return WrapperResponse.success(wxConfigCmdService.batchgetMaterial(batchgetMaterialCmd));
    }

}
