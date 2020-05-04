package com.jinshuo.mall.service.wx.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.utils.HttpsClientUtil;
import com.jinshuo.core.utils.RedisUtil;
import com.jinshuo.mall.service.wx.application.cmd.*;
import com.jinshuo.mall.service.wx.application.dto.*;
import com.jinshuo.mall.service.wx.constant.OpenPlatformConfig;
import com.jinshuo.mall.service.wx.constant.OpenRedisKeyConstant;
import com.jinshuo.mall.service.wx.exception.WsBizException;
import com.jinshuo.mall.service.wx.exception.WsReturnCode;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信开放平台第三方平台接口
 * @author dongyh
 * @Title: WxOpenPlatform
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/7/19 17:41
 */
@Slf4j
public class WxOpenPlatform {
    /**
     * 授权注册页面扫码授权(web方式)
     */
    public static final String AUTH_WEB_URL ="https://mp.weixin.qq.com/cgi-bin/" +
            "?component_appid=xxxx" +
            "&pre_auth_code=xxxxx" +
            "&redirect_uri=xxxx" +
            "&auth_type=xxx";


    /**
     * 移动端跨度授权链接（mobile方式）
     */
    public static final String AUTH_MOBILE_URL ="https://mp.weixin.qq.com/safe/bindcomponent" +
            "?action=bindcomponent&auth_type=3" +
            "&no_scan=1" +
            "&component_appid=" +
            "&pre_auth_code=xxxxx" +
            "&redirect_uri=xxxx" +
            "&auth_type=xxx" +
            "&biz_appid=xxxx#wechat_redirect";


    /**
     * 获取第三方令牌（令牌有效期2小时）
     */
    public static final String API_COMPONENT_TOKEN ="https://api.weixin.qq.com/cgi-bin/component/api_component_token";


    /**
     * 获取第三方平台预授码
     */
    public static final String PRE_AUTH_CODE ="https://api.weixin.qq.com/cgi-bin/component/" +
            "api_create_preauthcode?component_access_token=COMPONENT_ACCESS_TOKEN";


    /**
     * 第三方平台获取授权信息
     */
    public static final String API_QUERY_AUTH ="https://api.weixin.qq.com/cgi-bin/component/api_query_auth" +
            "?component_access_token=COMPONENT_ACCESS_TOKEN";


    /**
     * 获取第三方平台调用令牌（令牌有效期2小时）
     */
    public static final String API_AUTHORIZER_TOKEN ="https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token" +
            "?component_access_token=COMPONENT_ACCESS_TOKEN";


    /**
     * 微信第三方平台代公众号发起网页授权
     */
    public static final String OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=APPID&code=CODE&grant_type=authorization_code" +
            "&component_appid=COMPONENT_APPID&component_access_token=COMPONENT_ACCESS_TOKEN";


    /**
     * 网页获取用户信息
     */
    public static final String WEB_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN"
            + "&openid=OPENID"
            + "&lang=zh_CN";

    /**
     * 获取小程序基本信息
     */
    public static final  String GET_ACCOUNT_BASICINFO_URL = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo?access_token=ACCESS_TOKEN";

    /**
     * 设置服务器域名
     */
    public static final  String MODIFY_DOMAIN_URL = "https://api.weixin.qq.com/wxa/modify_domain?access_token=ACCESS_TOKEN";

    /**
     * 设置业务域名
     */
    public static final  String WEB_VIEW_DOMAIN_URL = "https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=ACCESS_TOKEN";


    /**
     * 绑定微信用户为体验者
     */
    public static final  String BIND_TESTER_URL = "https://api.weixin.qq.com/wxa/bind_tester?access_token=ACCESS_TOKEN";


    /**
     * 解除绑定体验者
     */
    public static final  String UNBIND_TESTER_URL = "https://api.weixin.qq.com/wxa/unbind_tester?access_token=ACCESS_TOKEN";

    /**
     * 上传小程序
     */
    public static final String COMMIT_URL = "https://api.weixin.qq.com/wxa/commit?access_token=ACCESS_TOKEN";


    /**
     * 获取体验版二维码
     */
    public static final String GET_QRCODE_URL = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=ACCESS_TOKEN";

    /**
     * 提交审核
     * 在调用上传代码接口为小程序上传代码后，可以调用本接口，将上传的代码提交审核
     */
    public static final String SUBMIT_AUDIT_URL = "https://api.weixin.qq.com/wxa/submit_audit?access_token=ACCESS_TOKEN";

    /**
     * 查询最新一次提交的审核状态
     */
    public static final String GET_LATEST_AUDITSTATUS_URL = "https://api.weixin.qq.com/wxa/get_latest_auditstatus?access_token=ACCESS_TOKEN";

    /**
     * 发布已通过审核的小程序
     */
    public static final String RELEASE_URL = "https://api.weixin.qq.com/wxa/release?access_token=ACCESS_TOKEN";



    /**
     * 获取第三方平台推送的component_verify_ticket
     * @param xml 微信推送到平台设置的url数据
     * @param appid 第三方平台appid
     * @return
     */
    public static String getComponentVerifyTicket(String xml,String appid){
        //从redis中获取component_verify_ticket
        log.info("微信推送的信息为：{}",xml);
        XStream xStream = new XStream();
        xStream.alias("xml", ComponentVerifyTicketDto.class);
        ComponentVerifyTicketDto componentVerifyTicketDto = (ComponentVerifyTicketDto)xStream.fromXML(xml);
        String componentVerifyTicket = componentVerifyTicketDto.getComponentVerifyTicket();
        log.info("微信推送的ticket为：{}",componentVerifyTicket);
        //将新的ticket存入redis
        RedisUtil.setex(OpenRedisKeyConstant.COMPONENT_VERIFY_TICKET_KEY + appid,componentVerifyTicket,2*60*60);
        return componentVerifyTicket;
    }


    /**
     * 获取令牌（有效时间2小时）
     * @param wxApiComponentTokenCmd 获取令牌的参数
     * @return
     */
    public static String getApiComponentToken(WxApiComponentTokenCmd wxApiComponentTokenCmd) throws Exception {
        String json = JSONObject.toJSONString(wxApiComponentTokenCmd);
        JSONObject jsonObject = HttpsClientUtil.doPost2(API_COMPONENT_TOKEN,json);
        log.info("获取第三方平台令牌地址为：{}，返回信息为：{}",API_COMPONENT_TOKEN,jsonObject.toJSONString());
        if(StringUtils.isNotBlank(jsonObject.getString("errcode"))){
            log.error("获取第三方平台令牌失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205011.getMsg(),WsReturnCode.WS205011.getCode());
        }
        String componentAccessToken = jsonObject.getString("component_access_token");
        //获取到的component_access_token放入redis
        RedisUtil.setex(OpenRedisKeyConstant.COMPONENT_ACCESS_TOKEN_KEY + wxApiComponentTokenCmd.getComponent_appid(),componentAccessToken,6600);
        return componentAccessToken;
    }

    /**
     * 获取第三方平台预授码 授权码有效期为10分钟
     * @param component_access_token 令牌
     * @param component_appid 第三方平台 appid
     * @return
     */
    public static String getPreAuthCode(String component_access_token,String component_appid) throws Exception {
        String preAuthCode = RedisUtil.getStr(OpenRedisKeyConstant.PRE_AUTH_CODE_KEY + component_appid);
        if(StringUtils.isBlank(preAuthCode)) {
            String url = PRE_AUTH_CODE.replace("COMPONENT_ACCESS_TOKEN",component_access_token);
            JSONObject requestJsonObject = new JSONObject();
            //第三方平台appid
            requestJsonObject.put("component_appid",component_appid);
            JSONObject jsonObject = HttpsClientUtil.doPost2(url,requestJsonObject.toJSONString());
            log.info("获取第三方平台预授码地址为：{}，输入参数为{},返回信息为：{}",
                    url,
                    requestJsonObject.toJSONString(),
                    jsonObject.toJSONString());
            if(StringUtils.isNotBlank(jsonObject.getString("errcode"))){
                log.error("获取第三方平台预授码，errorCode："+jsonObject.getString("errcode")
                        +",errmsg:"+jsonObject.getString("errmsg") );
                throw new WsBizException(WsReturnCode.WS205010.getMsg(),WsReturnCode.WS205010.getCode());
            }
            preAuthCode = jsonObject.getString("pre_auth_code");
            RedisUtil.setex(OpenRedisKeyConstant.PRE_AUTH_CODE_KEY + component_appid,preAuthCode,10*60);
        }
        return preAuthCode;
    }


    /**
     * 查询授权信息
     * @param component_access_token
     * @param component_appid
     * @param authorization_code
     * @return
     * @throws Exception
     */
    public static OpenPlatformAuthInfoDto getApiQueryAuth(String component_access_token, String component_appid, String authorization_code) throws Exception {
        String url = API_QUERY_AUTH.replace("COMPONENT_ACCESS_TOKEN",component_access_token);
        JSONObject requestJsonObject = new JSONObject();
        //第三方平台appid
        requestJsonObject.put("component_appid",component_appid);
        //授权码, 会在授权成功时返回给第三方平台
        requestJsonObject.put("authorization_code",authorization_code);
        String json = JSONObject.toJSONString(requestJsonObject);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,json);
        log.info("获取第三方平台授权信息地址为：{}，输入参数为{},返回信息为：{}",
                url,
                requestJsonObject.toJSONString(),
                jsonObject.toJSONString());
        if(StringUtils.isNotBlank(jsonObject.getString("errcode"))){
            log.error("获取第三方平台授权失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205012.getMsg(),WsReturnCode.WS205012.getCode());
        }

        OpenPlatformAuthDto openPlatformAuthDto = JSONObject.toJavaObject(jsonObject,OpenPlatformAuthDto.class);
        return openPlatformAuthDto.getAuthorization_info();
    }



    /**
     * 获取/刷新接口调用令牌
     * @param component_access_token 令牌
     * @param component_appid 第三方平台 appid
     * @param authorizer_appid 授权方 appid
     * @param authorizer_refresh_token 刷新令牌，获取授权信息时得到
     * @return
     * @throws Exception
     */
    public static OpenAuthAccessTokenDto getApiAuthorizerToken(String component_access_token, String component_appid
            , String authorizer_appid, String authorizer_refresh_token) throws Exception {
        String url = API_AUTHORIZER_TOKEN.replace("COMPONENT_ACCESS_TOKEN",component_access_token);

        JSONObject requestJsonObject = new JSONObject();
        //第三方平台appid
        requestJsonObject.put("component_appid",component_appid);
        //授权方 appid
        requestJsonObject.put("authorizer_appid",authorizer_appid);
        //刷新令牌，获取授权信息时得到
        requestJsonObject.put("authorizer_refresh_token",authorizer_refresh_token);
        String json = JSONObject.toJSONString(requestJsonObject);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,json);
        log.info("获取第三方平台获取/刷新接口调用令牌地址为：{}，输入参数为{},返回信息为：{}",
                url,
                requestJsonObject.toJSONString(),
                jsonObject.toJSONString());
        if(StringUtils.isNotBlank(jsonObject.getString("errcode"))){
            log.error("获取第三方平台获取/刷新接口调用令牌失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205013.getMsg(),WsReturnCode.WS205013.getCode());
        }

        OpenAuthAccessTokenDto openAuthAccessTokenDto = JSONObject.toJavaObject(jsonObject,OpenAuthAccessTokenDto.class);
        return openAuthAccessTokenDto;
    }


    /**
     * (对访问该链接的客户端有 IP 白名单的要求)
     * 使用网页授权获取的code换取access_token
     * @param appid 公众号的 appid
     * @param code 网页返回的code
     * @param grant_type 填 authorization_code
     * @param component_appid 服务开发方的 appid
     * @param component_access_token 服务开发方的 access_token
     * @return
     */
    public static AccessTokenDto getAccessToken(String appid, String code, String grant_type, String component_appid, String component_access_token) throws Exception {
        String url =OPEN_ACCESS_TOKEN_URL.replaceFirst("APPID",appid)
                .replaceFirst("CODE",code)
                .replaceFirst("COMPONENT_APPID",component_appid)
                .replaceFirst("COMPONENT_ACCESS_TOKEN",component_access_token);
        log.info("调用地址：{}",url);
        JSONObject jsonObject = HttpsClientUtil.doGet(url);
        //如果调用微信错误的话
        if(StringUtils.isNotBlank(jsonObject.getString("errcode"))){
            log.error("获取网页授权服务方微信accessToken失败，errorCode：{} ,errmsg:{}，当前code：{}",jsonObject.getString("errcode")
                    ,jsonObject.getString("errmsg"),code);
            if("40001".equals(jsonObject.getString("errcode"))){
                //过期清除accessToken
                RedisUtil.del(OpenRedisKeyConstant.COMPONENT_ACCESS_TOKEN_KEY + OpenPlatformConfig.APPID);
            }
            throw new WsBizException(WsReturnCode.WS205005.getMsg(),WsReturnCode.WS205005.getCode());
        }
        AccessTokenDto accessTokenDto = JSONObject.toJavaObject(jsonObject,AccessTokenDto.class);
        return accessTokenDto;
    }


    /**
     * 网页授权获取用户信息
     * @param openid 用户opendid
     * @param access_token 获取网页信息的accessToken
     * @return
     */
    public static WxUserInfo getWxWebUserInfo(String openid, String access_token)throws Exception{
        String infoUrl =WEB_INFO_URL.replace("ACCESS_TOKEN",access_token)
                .replace("OPENID",openid);
        JSONObject jsonObject = HttpsClientUtil.doGet(infoUrl);
        if(StringUtils.isNotBlank(jsonObject.getString("errcode"))){
            log.error("网页获取微信用户信息失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205006.getMsg(),WsReturnCode.WS205006.getCode());
        }
        WxUserInfo wxUserInfo = JSONObject.toJavaObject(jsonObject,WxUserInfo.class);
        return wxUserInfo;
    }

    /**
     * 获取基本信息
     * 调用本 API 可以获取小程序的基本信息
     * @param access_token
     * @return
     * @throws Exception
     */
    public static AccountBasicinfoDto getAccountBasicinfo(String access_token) throws Exception {
        String url = GET_ACCOUNT_BASICINFO_URL.replaceFirst("ACCESS_TOKEN",access_token);
        JSONObject jsonObject = HttpsClientUtil.doGet(url);
        log.info("获取小程序信息返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("获取小程序基本信息失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205019.getMsg(),WsReturnCode.WS205019.getCode());
        }
        AccountBasicinfoDto accountBasicinfoDto = JSONObject.toJavaObject(jsonObject,AccountBasicinfoDto.class);
        return accountBasicinfoDto;
    }


    /**
     * 设置服务器域名
     * @param wxMiniSetServicesDomianCmd
     * @throws Exception
     */
    public static WxMiniSetServicesDomianDto modifyDomain(WxMiniSetServicesDomianCmd wxMiniSetServicesDomianCmd) throws Exception {
        String url = MODIFY_DOMAIN_URL.replaceFirst("ACCESS_TOKEN",wxMiniSetServicesDomianCmd.getAccess_token());
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(wxMiniSetServicesDomianCmd));
        log.info("第三方平台设置服务器域名返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台设置服务器域名失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205020.getMsg(),WsReturnCode.WS205020.getCode());
        }
        WxMiniSetServicesDomianDto wxMiniSetServicesDomianDto = JSONObject.toJavaObject(jsonObject,WxMiniSetServicesDomianDto.class);
        return wxMiniSetServicesDomianDto;
    }


    /**
     * 设置业务域名
     * @param WxMiniWebViewDomianCmd
     * @throws Exception
     */
    public static JSONObject webViewDomin(WxMiniWebViewDomianCmd WxMiniWebViewDomianCmd) throws Exception {
        String url = WEB_VIEW_DOMAIN_URL.replaceFirst("ACCESS_TOKEN",WxMiniWebViewDomianCmd.getAccess_token());
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(WxMiniWebViewDomianCmd));
        log.info("第三方平台设置业务域名返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台设置业务域名失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205021.getMsg(),WsReturnCode.WS205021.getCode());
        }
        return jsonObject;
    }



    /**
     * 绑定微信用户为体验者
     * 第三方平台在帮助旗下授权的小程序提交代码审核之前，可先让小程序运营者体验，
     * 体验之前需要将运营者的个人微信号添加到该小程序的体验者名单中。
     * 注意： 如果运营者同时也是该小程序的管理员，则无需绑定，管理员默认有体验权限
     * @param access_token 调用令牌
     * @param wechatid 微信号
     * @throws Exception
     */
    public static JSONObject bindTester(String access_token,String wechatid) throws Exception {
        String url = BIND_TESTER_URL.replaceFirst("ACCESS_TOKEN",access_token);
        Map<String,String> map = new HashMap<>();
        map.put("wechatid",wechatid);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(map));

        log.info("第三方平台绑定体验者返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台第三方平台绑定体验者失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205022.getMsg(),WsReturnCode.WS205022.getCode());
        }
        return jsonObject;
    }



    /**
     * 调用本接口可以将特定微信用户从小程序的体验者列表中解绑
     * @param wxMiniUnbindTesterCmd
     * @throws Exception
     */
    public static JSONObject unbindTester(WxMiniUnbindTesterCmd wxMiniUnbindTesterCmd) throws Exception {
        String url = UNBIND_TESTER_URL.replaceFirst("ACCESS_TOKEN",wxMiniUnbindTesterCmd.getAccess_token());
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(wxMiniUnbindTesterCmd));
        log.info("第三方平台解除绑定体验者返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台第三方平台解除绑定体验者失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205023.getMsg(),WsReturnCode.WS205023.getCode());
        }
        return jsonObject;
    }

    /**
     * 上传小程序代码
     * @param wxMiniCommitCmd
     * @return
     * @throws Exception
     */
    public static JSONObject commitCode(WxMiniCommitCmd wxMiniCommitCmd) throws Exception {
        String url = COMMIT_URL.replaceFirst("ACCESS_TOKEN",wxMiniCommitCmd.getAccess_token());
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(wxMiniCommitCmd));
        log.info("第三方平台上传小程序代码返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台上传小程序代码失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205024.getMsg(),WsReturnCode.WS205024.getCode());
        }
        return jsonObject;
    }


    /**
     * 获取体验小程序二维码
     * @param access_token
     * @param path
     * @return
     * @throws Exception
     */
    public static JSONObject getQrcode(String access_token,String path) throws Exception {
        String url = GET_QRCODE_URL.replaceFirst("ACCESS_TOKEN",access_token);
        Map<String,String> map = new HashMap<>();
        map.put("path",path);
        JSONObject jsonObject = HttpsClientUtil.doGet(url,map);
        log.info("第三方平台获取小程序体验版二维码返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台获取小程序体验版二维码，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205025.getMsg(),WsReturnCode.WS205025.getCode());
        }
        return jsonObject;
    }



    /**
     * 提交审核小程序
     * @param wxMiniCommitAuditCmd
     * @return
     * @throws Exception
     */
    public static JSONObject commitAudit(WxMiniCommitAuditCmd wxMiniCommitAuditCmd) throws Exception {
        String url = SUBMIT_AUDIT_URL.replaceFirst("ACCESS_TOKEN",wxMiniCommitAuditCmd.getAccess_token());
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(wxMiniCommitAuditCmd));
        log.info("第三方平台审核小程序返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台审核小程序失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205026.getMsg(),WsReturnCode.WS205026.getCode());
        }
        return jsonObject;
    }


    /**
     * 查询最新一次提交的审核状态
     * @param access_token 调用令牌
     * @return
     * @throws Exception
     */
    public static MiniLasterAuditStatusDto getLatestAditStatus(String access_token) throws Exception {
        String url = GET_LATEST_AUDITSTATUS_URL.replaceFirst("ACCESS_TOKEN",access_token);
        JSONObject jsonObject = HttpsClientUtil.doGet(url);
        log.info("第三方平台查询最新一次提交的审核状态返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台查询最新一次提交的审核状态失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205027.getMsg(),WsReturnCode.WS205027.getCode());
        }
        MiniLasterAuditStatusDto miniLasterAuditStatusDto = JSONObject.toJavaObject(jsonObject,MiniLasterAuditStatusDto.class);
        return miniLasterAuditStatusDto;
    }



    /**
     * 调用本接口可以发布最后一个审核通过的小程序代码版本
     * @param access_token
     */
    public static JSONObject release(String access_token) throws Exception {
        String url = RELEASE_URL.replaceFirst("ACCESS_TOKEN",access_token);
        JSONObject jsonObject = HttpsClientUtil.doPost(url);
        log.info("第三方平台发布已通过审核的小程序返回结果为：{}",jsonObject.toJSONString());
        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            log.error("第三方平台发布已通过审核的小程序状态失败，errorCode："+jsonObject.getString("errcode")
                    +",errmsg:"+jsonObject.getString("errmsg") );
            throw new WsBizException(WsReturnCode.WS205028.getMsg(),WsReturnCode.WS205028.getCode());
        }
        return jsonObject;
    }

    public static void main(String[] args) {
       /* String xml = "<xml><AppId><![CDATA[wx90ea14d1579219ad]]></AppId>\n" +
                "<CreateTime>1571123234</CreateTime>\n" +
                "<InfoType><![CDATA[component_verify_ticket]]></InfoType>\n" +
                "<ComponentVerifyTicket><![CDATA[ticket@@@UiOpYSEEsvhShWenejR1RXlzq8s12fiTBSiXM0tUrgH9UAaEsLnw_JAeeH6YQlRCRvM8dZbyEr1Bb-CTL5kW9A]]></ComponentVerifyTicket>\n" +
                "</xml>";
        String appid="wx90ea14d1579219ad";
        XStream xStream = new XStream();
        xStream.alias("xml", ComponentVerifyTicketDto.class);
        ComponentVerifyTicketDto componentVerifyTicketDto = (ComponentVerifyTicketDto)xStream.fromXML(xml);
        log.info("获取到的ticket:{}",componentVerifyTicketDto.getComponentVerifyTicket());*/
       String json = "{\"authorization_info\":{\"authorizer_appid\":\"wx7c1f511f8e8c783a\",\"authorizer_access_token\":\"26_9YDox93n-NlKLyGo-hR8pm5ccmPKmgj_2kMIJodfKhDpY4dbW_Xav3KgVoZpVd3Pb7MJcdK1klmRKyWVBcUtDqiznopD5CXtLml8Xz_iUc5Bo461K3uMWyF0f3oWr1dAk2UaM12ZyzKVqvbVZWWgAGDPMM\",\"authorizer_refresh_token\":\"refreshtoken@@@Lp6wHXG9g_EoXLuKy8o79EX-Iyg8kSCtS3KXlQDgmsI\",\"expires_in\":7200,\"func_info\":[{\"funcscope_category\":{\"id\":1}},{\"funcscope_category\":{\"id\":15}},{\"funcscope_category\":{\"id\":4}},{\"funcscope_category\":{\"id\":7}},{\"funcscope_category\":{\"id\":2}},{\"funcscope_category\":{\"id\":3}},{\"funcscope_category\":{\"id\":11}},{\"funcscope_category\":{\"id\":6}},{\"funcscope_category\":{\"id\":8}},{\"funcscope_category\":{\"id\":9}},{\"funcscope_category\":{\"id\":23}},{\"funcscope_category\":{\"id\":24},\"confirm_info\":{\"need_confirm\":0,\"can_confirm\":0,\"already_confirm\":0}},{\"funcscope_category\":{\"id\":26}},{\"funcscope_category\":{\"id\":27},\"confirm_info\":{\"need_confirm\":0,\"can_confirm\":0,\"already_confirm\":0}},{\"funcscope_category\":{\"id\":33},\"confirm_info\":{\"need_confirm\":0,\"can_confirm\":0,\"already_confirm\":0}},{\"funcscope_category\":{\"id\":34}},{\"funcscope_category\":{\"id\":44},\"confirm_info\":{\"need_confirm\":0,\"can_confirm\":0,\"already_confirm\":0}},{\"funcscope_category\":{\"id\":46}}]}}";

        OpenPlatformAuthDto openPlatformAuthDto = JSONObject.toJavaObject(JSON.parseObject(json),OpenPlatformAuthDto.class);
        log.info("111111");
    }
}
