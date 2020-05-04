package com.jinshuo.mall.service.wx.webservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.utils.RedisUtil;
import com.jinshuo.mall.service.wx.application.cmd.WxBatchgetMaterialCmd;
import com.jinshuo.mall.service.wx.application.cmd.WxQrcodeCmd;
import com.jinshuo.mall.service.wx.application.dto.SignDto;
import com.jinshuo.mall.service.wx.application.dto.WxQrcodeDto;
import com.jinshuo.mall.service.wx.application.dto.WxReceiveMessageDto;
import com.jinshuo.mall.service.wx.application.service.WxMp;
import com.jinshuo.mall.service.wx.constant.WxMpRedisKeyConstant;
import com.jinshuo.mall.service.wx.utils.SHA1;
import com.jinshuo.mall.service.wx.utils.Sign;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 微信公众平台接口
 *
 * @author dongyh
 * @Title: WxMpAgentService
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 16:07
 */
@Slf4j
public class WxMpAgentService {


    /**
     * 验证微信公众平台
     *
     * @param timestamp 时间戳
     * @param nonce     随机串
     * @param signature 签名
     * @throws Exception
     */
    public static String callBackGet(String token, String signature, String timestamp, String nonce, String echostr) throws NoSuchAlgorithmException, IOException {
        String tmpStr = SHA1.getSHA1(token, timestamp, nonce);
        log.info(" -- 公众平台接收到的tmpStr：{}   ", tmpStr);
        log.info(" -- 公众平台接收到的signature：{}   ", signature);
        if (tmpStr.equals(signature)) {
            return echostr;
        } else {
            return "error";
        }
    }

    /**
     * 微信接收事件推送（将xml转成对象）
     */
    public static WxReceiveMessageDto getWxReceiveMessage(String xml) {
        XStream xStream = new XStream();
        xStream.alias("xml", WxReceiveMessageDto.class);
        WxReceiveMessageDto wxReceiveMessageDto = (WxReceiveMessageDto) xStream.fromXML(xml);
        return wxReceiveMessageDto;
    }


    /**
     * 创建临时带参数二维码
     *
     * @param wxQrcodeCmd
     * @return
     * @throws Exception
     */
    public static String createQrcode(String token, WxQrcodeCmd wxQrcodeCmd) throws Exception {
        WxQrcodeDto wxQrcodeDto = WxMp.createQrcode(token, JSON.toJSONString(wxQrcodeCmd));
        String url = wxQrcodeDto.getUrl();
        return url;
    }

    /**
     * 公众平台jssdk
     *
     * @param access_token 调用凭证
     * @param appid        公众号appid
     * @param url          当前页面url
     * @return
     * @throws Exception
     */
    public static SignDto createSign(String access_token, String appid, String url) throws Exception {
        String jsapi_ticket = RedisUtil.getStr(WxMpRedisKeyConstant.JSAPI_TICKE_KEY + appid);
        //如果为空，则重新调接口获取，并保存到redis中
        if (StringUtils.isBlank(jsapi_ticket)) {
            jsapi_ticket = WxMp.getJsapiTicket(access_token);
            RedisUtil.setex(WxMpRedisKeyConstant.JSAPI_TICKE_KEY + appid, jsapi_ticket, 5400);
        }

        Map<String, String> ret = Sign.sign(jsapi_ticket, URLDecoder.decode(url));
        SignDto signDto = SignDto.builder()
                .appId(appid)
                .nonceStr(ret.get("nonceStr"))
                .timestamp(ret.get("timestamp"))
                .signature(ret.get("signature"))
                .build();
        return signDto;
    }


    /**
     * 获取素材列表
     *
     * @param token               调用凭证
     * @param batchgetMaterialCmd
     * @return
     * @throws Exception
     */
    public static JSONObject batchgetMaterial(String token, WxBatchgetMaterialCmd batchgetMaterialCmd) throws Exception {
        JSONObject backjson = WxMp.batchgetMaterial(token, JSON.toJSONString(batchgetMaterialCmd));
        return backjson;
    }
}
