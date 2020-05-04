package com.jinshuo.mall.service.wx.webservice;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.utils.RedisUtil;
import com.jinshuo.mall.service.wx.application.cmd.WxApiComponentTokenCmd;
import com.jinshuo.mall.service.wx.application.dto.*;
import com.jinshuo.mall.service.wx.application.service.WxMp;
import com.jinshuo.mall.service.wx.application.service.WxOpenPlatform;
import com.jinshuo.mall.service.wx.constant.OpenPlatformConfig;
import com.jinshuo.mall.service.wx.constant.OpenRedisKeyConstant;
import com.jinshuo.mall.service.wx.utils.AesException;
import com.jinshuo.mall.service.wx.utils.WXBizMsgCrypt;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 微信开放平台
 * @author dongyh
 * @Title: WxOpenAgentService
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 16:07
 */
@Slf4j
public class WxOpenAgentService {
    /**
     * 获取微信推送的验证票据（component_verify_ticket）
     * @param body 推送的xml
     * @param encrypt_type 加密方式
     * @param timestamp  时间戳
     * @param nonce 随机串
     * @param msg_signature  签名串
     * @param signature 签名
     * @throws Exception
     */
    public static void getComponentVerifyTicket(String body, String encrypt_type,
                                         String timestamp, String nonce,
                                         String msg_signature, String signature) throws Exception {
        log.info("接收到的原始xml为:{}", body.toString());
        log.info("接收到的encrypt_type:{}", encrypt_type);
        log.info("接收到的timestamp:{}", timestamp);
        log.info("接收到的nonce:{}", nonce);
        log.info("接收到的msg_signature:{}", msg_signature);
        log.info("接收到的signature:{}", signature);
        String encodingAesKey = OpenPlatformConfig.ENCODINGAESKEY;
        String token = OpenPlatformConfig.TOKEN;
        String appid = OpenPlatformConfig.APPID;
        //手动替换AppId字段
        String xml =body.replace("AppId","ToUserName");
        log.info("替换之后的xml为: {}",xml);
        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appid);
        String resultXml = pc.decryptMsg(msg_signature, timestamp, nonce, xml);
        log.info("解密后明文: {}",resultXml);
        //将获取到的component_verify_ticket保存进入redis
        WxOpenPlatform.getComponentVerifyTicket(resultXml,appid);
    }


    /**
     *  步骤 1：第三方平台方获取预授权码（pre_auth_code）
     */
    public static PreAuthCodeDto getPreAuthCode() throws Exception {
        //判断预授权码是否过期
        String preAuthCode = RedisUtil.getStr(OpenRedisKeyConstant.PRE_AUTH_CODE_KEY + OpenPlatformConfig.APPID);
        if(StringUtils.isBlank(preAuthCode)) {
            //获取令牌
            String componentAccessToken = getApiComponentToken();
            //用令牌获取预授权码
            preAuthCode = WxOpenPlatform.getPreAuthCode(componentAccessToken,OpenPlatformConfig.APPID);
        }
        //构建返回前端参数
        PreAuthCodeDto preAuthCodeDto = PreAuthCodeDto.builder()
                .pre_auth_code(preAuthCode)
                .component_appid(OpenPlatformConfig.APPID)
                .redirect_uri("")
                .build();
        return preAuthCodeDto;
    }

    /**
     * 使用授权码获取授权信息
     * @param authorizationCode
     * @return
     */
    public static OpenPlatformAuthInfoDto getApiQueryAuth(String authorizationCode) throws Exception {
        //获取令牌
        String componentAccessToken = getApiComponentToken();
        OpenPlatformAuthInfoDto openPlatformAuthInfoDto =WxOpenPlatform.getApiQueryAuth(componentAccessToken,
                OpenPlatformConfig.APPID,authorizationCode);
        //将获取到的authorizer_access_token缓存起来
        RedisUtil.setex(OpenRedisKeyConstant.AUTHORIZER_ACCESS_TOKEN_KEY + openPlatformAuthInfoDto.getAuthorizer_appid(),
                openPlatformAuthInfoDto.getAuthorizer_access_token(),5400);
        return openPlatformAuthInfoDto;
    }

    /**
     * 获取调用接口的令牌
     * @param appid 授权方appid
     * @param authorizerRefreshToken 授权方的刷新令牌
     * @return
     * @throws Exception
     */
    public static String getApiAuthorizerToken(String appid,String authorizerRefreshToken) throws Exception {
        //判断调用令牌是否失效
        String authorizerAccessToken = RedisUtil.getStr(OpenRedisKeyConstant.AUTHORIZER_ACCESS_TOKEN_KEY + appid);
        if(StringUtils.isBlank(authorizerAccessToken)){
           String componentAccessToken = getApiComponentToken();
            //调用刷新令牌接口，重新获取令牌
            OpenAuthAccessTokenDto openAuthAccessTokenDto = WxOpenPlatform.getApiAuthorizerToken(componentAccessToken,
                    OpenPlatformConfig.APPID,appid,authorizerRefreshToken);

            authorizerAccessToken =  openAuthAccessTokenDto.getAuthorizer_access_token();
            //将获取到的authorizer_access_token缓存起来
            RedisUtil.setex(OpenRedisKeyConstant.AUTHORIZER_ACCESS_TOKEN_KEY + appid,
                    authorizerAccessToken,5400);
        }
        return authorizerAccessToken;
    }

    /**
     * 第三方平台代理公众号获取网页accessToken
     * @param appid
     * @param code
     * @return
     * @throws Exception
     */
    public static AccessTokenDto getWebAccessToken(String appid, String code) throws Exception {
        String componentAccessToken = getApiComponentToken();
        AccessTokenDto accessTokenDto = WxOpenPlatform.getAccessToken(appid,
                code,"authorization_code",OpenPlatformConfig.APPID,componentAccessToken);
        return accessTokenDto;
    }



     /**
     * 第三方平台代理公众号获取网页用户信息
     * @param openid 用户openid
     * @param accessToken 网页获取的accesstoken
     * @return
     * @throws Exception
     */
    public static WxUserInfo getWebUserInfo(String openid, String accessToken) throws Exception {
        WxUserInfo wxUserInfo = WxOpenPlatform.getWxWebUserInfo(openid,accessToken);
        return wxUserInfo;
    }



    /**
     * 获取第三方平台令牌
     * @return
     * @throws Exception
     */
    public static String getApiComponentToken() throws Exception {
        //从redis获取令牌
        String componentAccessToken = RedisUtil.getStr(OpenRedisKeyConstant.COMPONENT_ACCESS_TOKEN_KEY + OpenPlatformConfig.APPID);
        if(StringUtils.isBlank(componentAccessToken)) {
            String componentVerifyTicket = RedisUtil.getStr(OpenRedisKeyConstant.COMPONENT_VERIFY_TICKET_KEY + OpenPlatformConfig.APPID);

            //构造获取令牌的参数
            WxApiComponentTokenCmd wxApiComponentTokenCmd =  WxApiComponentTokenCmd.builder()
                    .component_appid(OpenPlatformConfig.APPID)
                    .component_appsecret(OpenPlatformConfig.APPSECRET)
                    .component_verify_ticket(componentVerifyTicket)
                    .build();
            //用componentVerifyTicket去获取令牌
            componentAccessToken = WxOpenPlatform.getApiComponentToken(wxApiComponentTokenCmd);
        }
        return componentAccessToken;
    }


    /**
     * 第三方平台代公众号被动回复信息。必须加密
     * @param replyMsg 回复的消息
     * @return
     */
    public static String getPassiveRecovery(String replyMsg,String timestamp,String nonce) throws AesException {
        String encodingAesKey = OpenPlatformConfig.ENCODINGAESKEY;
        String token = OpenPlatformConfig.TOKEN;
        String appId = OpenPlatformConfig.APPID;
        //生成随机字符串
        //String timestamp = Long.toString(System.currentTimeMillis());
        //String nonce = RandomNonceUtils.getNonce();

        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
        String message = pc.encryptMsg(replyMsg, timestamp, nonce);
        System.out.println("加密后: " + message);
        return message;
    }


    /**
     * 开放平台全网发布
     * 测试公众号处理用户消息
     * @param wxReceiveMessageDto
     */
    public static String testPushToNet(WxReceiveMessageDto wxReceiveMessageDto,String timestamp,String nonce) throws Exception {
        String returnMsg =null;
        String messageType = wxReceiveMessageDto.getMsgType();
        String toUserName = wxReceiveMessageDto.getToUserName();
        String fromUserName = wxReceiveMessageDto.getFromUserName();
        String content = wxReceiveMessageDto.getContent();
        log.info("获取到的content为：{}",content);
        if("text".equals(messageType) && "TESTCOMPONENT_MSG_TYPE_TEXT".equals(content)){
            //如果是微信全网测试正好
            if("gh_3c884a361561".equals(toUserName)){
                returnMsg = rebackTextMessage(toUserName,fromUserName,"TESTCOMPONENT_MSG_TYPE_TEXT_callback",timestamp,nonce);
            }
        }
        return returnMsg;
    }


    /**
     * 全网发布
     * 测试公众号使用客服消息接口处理用户消息
     * @param wxReceiveMessageDto
     * @return
     * @throws Exception
     */
    public static Boolean testCustomMessage(WxReceiveMessageDto wxReceiveMessageDto) throws Exception {
        Boolean falg =false;
        String messageType = wxReceiveMessageDto.getMsgType();
        String toUserName = wxReceiveMessageDto.getToUserName();
        String fromUserName = wxReceiveMessageDto.getFromUserName();
        String content = wxReceiveMessageDto.getContent();
        log.info("获取到的content为：{}",content);

        if(StringUtils.isNotBlank(content)){
            if("text".equals(messageType) && content.contains("QUERY_AUTH_CODE")){
                if("gh_3c884a361561".equals(toUserName)){
                    //获取到的auth_code
                    String query_auth_code = content.replace("UERY_AUTH_CODE:","");
                    //使用授权码获取授权信息
                    OpenPlatformAuthInfoDto openPlatformAuthInfoDto = getApiQueryAuth(query_auth_code);
                    String authorizer_access_token = openPlatformAuthInfoDto.getAuthorizer_access_token();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("touser",fromUserName);
                    jsonObject.put("msgtype","text");

                    JSONObject textObject = new JSONObject();
                    textObject.put("content",query_auth_code+"_from_api");
                    jsonObject.put("text",textObject);

                    WxMp.sendCustomMessage(authorizer_access_token,jsonObject.toJSONString());
                    falg = true;
                }
            }
        }
        return falg;
    }


    /**
     * 被动回复文本信息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @param timestamp
     * @param nonce
     * @return
     * @throws AesException
     */
    public static String rebackTextMessage(String toUserName,String fromUserName,String content,String timestamp,String nonce) throws AesException {
        //回应文本消息
        ReplyTextMessage replyTextMessage = ReplyTextMessage.builder()
                .FromUserName(toUserName)
                .ToUserName(fromUserName)
                .CreateTime(System.currentTimeMillis())
                .MsgType("text")
                .Content(content)
                .build();
        XStream xStream = new XStream();
        xStream.alias("xml", ReplyTextMessage.class);
        //拼接成文本消息
        String  replayMessage = xStream.toXML(replyTextMessage);
        //将消息进行加密
        return getPassiveRecovery(replayMessage,timestamp,nonce);
    }



}
