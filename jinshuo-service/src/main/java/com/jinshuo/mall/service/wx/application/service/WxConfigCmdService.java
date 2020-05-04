package com.jinshuo.mall.service.wx.application.service;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.utils.XMLUtil;
import com.jinshuo.mall.domain.user.model.shop.Shop;
import com.jinshuo.mall.domain.user.model.wx.WxConfig;
import com.jinshuo.mall.service.user.application.cmd.BatchgetMaterialCmd;
import com.jinshuo.mall.service.user.application.cmd.ImageCmd;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.application.cmd.WxPushMessageCmd;
import com.jinshuo.mall.service.user.application.dto.WxConfigDto;
import com.jinshuo.mall.service.user.application.dto.WxMpUser;
import com.jinshuo.mall.service.user.mybatis.WxConfigRepo;
import com.jinshuo.mall.service.user.service.command.UserAccountCmdService;
import com.jinshuo.mall.service.user.service.query.ShopQueryService;
import com.jinshuo.mall.service.wx.application.cmd.WxBatchgetMaterialCmd;
import com.jinshuo.mall.service.wx.constant.OpenPlatformConfig;
import com.jinshuo.mall.service.wx.utils.AccessTokenUtils;
import com.jinshuo.mall.service.wx.webservice.WxMpAgentService;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author mgh
 * @Classname WxConfigCmdService
 * @Description 微信配置信息
 * @Date 2019/7/8 15:37
 * @Created by mgh
 */
@Slf4j
@Service
public class WxConfigCmdService {

    @Autowired
    private WxConfigRepo wxConfigRepo;

    @Autowired
    private UserAccountCmdService userAccountCmdService;

    @Autowired
    private ShopQueryService shopQueryService;

    /**
     * 验证服务器
     *
     * @param body
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String callBackGet(String body, String signature, String timestamp, String nonce, String echostr) throws NoSuchAlgorithmException, JDOMException, IOException {
        String token = "santu_jinshuo";
        return WxMpAgentService.callBackGet(token, signature, timestamp, nonce, echostr);
    }

    /**
     * 获取微信全部配置
     *
     * @param shopIdCmd
     */
    public WxConfig getWxConfig(ShopIdCmd shopIdCmd) {
        WxConfig wxConfig = wxConfigRepo.findByShopIdAndType(shopIdCmd.getShopId(), 0);
        if (wxConfig == null) {
            throw new UcBizException(UcReturnCode.UC200007.getMsg(), UcReturnCode.UC200007.getCode());
        }

        return wxConfig;
    }

    /**
     * 获取token
     *
     * @param shopIdCmd
     */
    public String getToken(ShopIdCmd shopIdCmd) throws Exception {
        String token = AccessTokenUtils.getOpenToken(shopIdCmd.getShopId());
        return token;
        /*WxConfig wxConfig = wxConfigRepo.findByShopIdAndType(shopIdCmd.getShopId(),0);
        if(wxConfig == null){
            throw new UcBizException(UcReturnCode.UC200007.getMsg(),UcReturnCode.UC200007.getCode());
        }

        return wxConfig;*/
    }


    /**
     * 获取微信配置
     *
     * @param shopIdCmd
     */
    public WxConfigDto getConfig(ShopIdCmd shopIdCmd) {
        WxConfig wxConfig = wxConfigRepo.findByShopIdAndType(shopIdCmd.getShopId(), 0);
        if (wxConfig == null) {
            throw new UcBizException(UcReturnCode.UC200007.getMsg(), UcReturnCode.UC200007.getCode());
        }
        Shop shop = shopQueryService.getById(shopIdCmd.getShopId());
        return new WxConfigDto(wxConfig.getAppId(), OpenPlatformConfig.APPID, 0, shop.getName(), shop.getLogo(), shop.getLinkMan(), shop.getPhone(), shop.getSketch());
    }


    /**
     * 亿麦茗茶微信接收消息接口
     *
     * @param body
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws
     * @throws IOException
     */
    public String callBackPost(String body, String signature, String timestamp, String nonce, String echostr) throws Exception {

        Long shopId = null;
        log.info("亿麦茗茶微信公众号返回的body信息：{}", body.toString());
        // xml请求解析
        Map<String, String> requestMap = XMLUtil.doXMLParse(body.toString());
        // 公众帐号
        String toUserName = requestMap.get("ToUserName");
        //亿麦茗茶
        if (toUserName.equals("gh_e32a3516fde9")) {
            shopId = 10089L;
        }
        //亿麦周边游
        else if (toUserName.equals("gh_e3177dff4ea3")) {
            shopId = DefaultShopId.SHOPID;
        }

        String returnMsg = eventAction(requestMap, shopId);
        return returnMsg;
    }

    /**
     * 微信接收事件处理
     *
     * @param requestMap
     * @param shopId
     * @param shopId
     */
    public String eventAction(Map<String, String> requestMap, Long shopId) throws Exception {
        String returnMsg = null;

        // 发送方帐号（open_id）
        String fromUserName = requestMap.get("FromUserName");

        // 公众帐号
        String toUserName = requestMap.get("ToUserName");
        // 消息类型
        String msgType = requestMap.get("MsgType");

        // 事件类型
        String event = requestMap.get("Event");

        String eventKey = requestMap.get("EventKey");
        if (StringUtils.isNotBlank(event)) {
            switch (event) {
                //扫码事件
                case "SCAN":
                    ;
                    //关注事件
                case "subscribe":
                    String inviteCode = null;
                    //判断是直接扫公众号二维码进来的还是扫别人二维码进来
                    if (StringUtils.isNotBlank(eventKey)) {
                        //判断是否包含扫码关键字
                        if (eventKey.contains("qrscene_")) {
                            //获取邀请码
                            inviteCode = eventKey.split("qrscene_")[1];
                        } else {
                            inviteCode = eventKey;
                        }
                    }

                    //公众平台
                    //WxConfig wxConfig = wxConfigRepo.findByShopIdAndType(shopId,0);
                    //String access_token = wxMpUserService.getToken(wxConfig);

                    String access_token = AccessTokenUtils.getOpenToken(shopId);
                    //第三方平台
                    //WxOpenAuth wxOpenAuth = WxOpenAuth.getWxAuth(shopId);
                    //String access_token = WxOpenAgentService.getApiAuthorizerToken(wxOpenAuth.getAppid(),wxOpenAuth.getAuthorizerRefreshToken());

                    //获取公众号微信个人信息
                    JSONObject userInfoObject = WxMp.getFansInfo(access_token, fromUserName);
                    WxMpUser wxMpUser = JSONObject.toJavaObject(userInfoObject, WxMpUser.class);
                    userAccountCmdService.loginAction(shopId, fromUserName, inviteCode, wxMpUser, "2");
                    break;
                //点击事件
                case "CLICK":
                    //如果是商务合作标签出来，则推送图片信息
                    if (eventKey.equals("businessCooperation")) {
                        if (shopId == 10088) {
                            ImageCmd imageCmd = ImageCmd.builder().MediaId("l0LL_nCzH0BCYAlHDl4o8GWfMS8b9qPckWyaQ6GRbiM").build();
                            WxPushMessageCmd wxPushMessageCmd = WxPushMessageCmd.builder()
                                    .FromUserName(toUserName)
                                    .ToUserName(fromUserName)
                                    .CreateTime(System.currentTimeMillis())
                                    .MsgType("image")
                                    .Image(imageCmd)
                                    .build();
                            XStream xStream = new XStream();
                            xStream.alias("xml", WxPushMessageCmd.class);
                            xStream.alias("Image", ImageCmd.class);
                            returnMsg = xStream.toXML(wxPushMessageCmd);
                            log.info("将对象转化为xml：{}", returnMsg);
                            log.info("返回的图片消息为：{}", returnMsg);
                        }
                        if (shopId == 10089) {
                            ImageCmd imageCmd = ImageCmd.builder().MediaId("ninnxadKzBBQuEU7P2VX3HoqaTZCXdd9-MX7D6GHaEA").build();
                            WxPushMessageCmd wxPushMessageCmd = WxPushMessageCmd.builder()
                                    .FromUserName(toUserName)
                                    .ToUserName(fromUserName)
                                    .CreateTime(System.currentTimeMillis())
                                    .MsgType("image")
                                    .Image(imageCmd)
                                    .build();
                            XStream xStream = new XStream();
                            xStream.alias("xml", WxPushMessageCmd.class);
                            xStream.alias("Image", ImageCmd.class);
                            returnMsg = xStream.toXML(wxPushMessageCmd);
                            log.info("将对象转化为xml：{}", returnMsg);
                            log.info("返回的图片消息为：{}", returnMsg);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return returnMsg;
    }

    /**
     * 获取素材列表(类型不同返回不同的对象)
     *
     * @param batchgetMaterialCmd
     * @return
     * @throws Exception
     */
    public JSONObject batchgetMaterial(BatchgetMaterialCmd batchgetMaterialCmd) throws Exception {
        /*WxConfig wxConfig = wxConfigRepo.findByShopIdAndType(batchgetMaterialCmd.getShopId(),0);
        String token = wxMpUserService.getToken(wxConfig);*/
        //通过第三方获取token
        String token = AccessTokenUtils.getOpenToken(batchgetMaterialCmd.getShopId());
        WxBatchgetMaterialCmd wxBatchgetMaterialCmd = WxBatchgetMaterialCmd.builder()
                .type(batchgetMaterialCmd.getType())
                .count(batchgetMaterialCmd.getCount())
                .offset(batchgetMaterialCmd.getOffset())
                .build();
        JSONObject backjson = WxMpAgentService.batchgetMaterial(token, wxBatchgetMaterialCmd);
        return backjson;
    }
}
