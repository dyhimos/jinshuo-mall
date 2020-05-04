package com.jinshuo.mall.service.wx.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.utils.HttpsClientUtil;
import com.jinshuo.core.utils.RedisUtil;
import com.jinshuo.mall.service.wx.application.assermbler.WxMpUserQueryAssembler;
import com.jinshuo.mall.service.wx.application.cmd.WxConfigCmd;
import com.jinshuo.mall.service.wx.application.cmd.WxSendTemplateMessageCmd;
import com.jinshuo.mall.service.wx.application.dto.WxQrcodeDto;
import com.jinshuo.mall.service.wx.constant.WxMpRedisKeyConstant;
import com.jinshuo.mall.service.wx.exception.WsBizException;
import com.jinshuo.mall.service.wx.exception.WsReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信公众平台接口返回数据管理
 *
 * @author dongyh
 * @Title: WxMp
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/7/19 17:41
 */
@Slf4j
public class WxMp {

    /**
     * 获取accessToken
     */
    public static final String accessToken = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 获取网页授权accessToken
     */
    public static final String WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID"
            + "&secret=SECRET"
            + "&code=CODE"
            + "&grant_type=authorization_code";
    ;

    /**
     * 网页获取用户信息
     */
    public static final String WEB_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN"
            + "&openid=OPENID"
            + "&lang=zh_CN";

    /**
     * 获取微信用户（openid）列表
     */
    public static final String FANS_OPENID_LIST_END_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

    /**
     * 获取微信用户（openid）列表包含下一个用户
     */
    public static final String FANS_OPENID_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get" +
            "?access_token=ACCESS_TOKEN" +
            "&next_openid=NEXT_OPENID";

    /**
     * 创建菜单
     */
    public static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 获取个人信息接口
     */
    public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 创建带参数的二维码
     */
    public static final String CREATE_QRCODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";


    /**
     * 获取素材列表列表
     */
    public static final String BATCHGET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";


    /**
     * 客服接口-发消息
     */
    public static final String SEND_CUSTOM_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    /**
     * 发送模板消息接口地址
     */
    public static final String SEND_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    /**
     * JS-SDK 中获取jsapi
     */
    public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";


    /**
     * 获取微信公众平台accessToken
     *
     * @param wxConfigCmd
     * @return
     * @throws Exception
     */
    public static String getToken(WxConfigCmd wxConfigCmd) throws Exception {
        //获取accessToken
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type", "client_credential");
        map.put("appid", wxConfigCmd.getAppid());
        map.put("secret", wxConfigCmd.getSecret());
        //从redis中获取access_token
        String token = RedisUtil.getStr(WxMpRedisKeyConstant.WX_ACCESS_TOKEN_KEY + wxConfigCmd.getAppid());
        log.info("获取到的转化获取accesstoken为：" + token);
        //如果为空，则重新调接口获取，并保存到redis中
        if (StringUtils.isBlank(token)) {
            JSONObject jsonObject = HttpsClientUtil.doGet(accessToken, map);
            //如果调用微信错误的话
            if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
                log.error("获取微信accessToken失败，errorCode：{} ,errmsg:{}，当前code：{}", jsonObject.getString("errcode")
                        , jsonObject.getString("errmsg"), wxConfigCmd.getCode());
                throw new WsBizException(WsReturnCode.WS205004.getMsg(), WsReturnCode.WS205004.getCode());
            }
            log.info("转化获取accesstoken为：" + jsonObject.toJSONString());
            token = jsonObject.getString("access_token");
            RedisUtil.del(WxMpRedisKeyConstant.WX_ACCESS_TOKEN_KEY + wxConfigCmd.getAppid());
            log.info("重新获取到的token为：" + token);
            //将新的access_token存入redis中
            RedisUtil.setex(WxMpRedisKeyConstant.WX_ACCESS_TOKEN_KEY + wxConfigCmd.getAppid(), token, 5400);
        }
        return token;
    }


    /**
     * 网页授权获取access_token
     *
     * @param wxConfigCmd 公众平台的参数
     * @return
     */
    public static JSONObject getGzptLoginObject(WxConfigCmd wxConfigCmd) throws Exception {
        String url = WEB_ACCESS_TOKEN_URL.replaceFirst("APPID", wxConfigCmd.getAppid())
                .replaceFirst("SECRET", wxConfigCmd.getSecret())
                .replaceFirst("CODE", wxConfigCmd.getCode());
        log.info("调用地址：{}", url);
        JSONObject jsonObject = HttpsClientUtil.doGet(url);
        //如果调用微信错误的话
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            log.error("获取网页授权微信accessToken失败，errorCode：{} ,errmsg:{}，当前code：{}", jsonObject.getString("errcode")
                    , jsonObject.getString("errmsg"), wxConfigCmd.getCode());
            throw new WsBizException(WsReturnCode.WS205005.getMsg(), WsReturnCode.WS205005.getCode());
        }
        return jsonObject;
    }


    /**
     * 网页授权获取用户信息
     *
     * @param openid       用户opendid
     * @param access_token 获取信息的accessToken
     * @return
     */
    public static JSONObject getGzhUserInfo(String openid, String access_token) throws Exception {
        String infoUrl = WEB_INFO_URL.replace("ACCESS_TOKEN", access_token)
                .replace("OPENID", openid);
        JSONObject jsonObject = HttpsClientUtil.doGet(infoUrl);
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            log.error("网页获取微信用户信息失败，errorCode：" + jsonObject.getString("errcode")
                    + ",errmsg:" + jsonObject.getString("errmsg"));
            throw new WsBizException(WsReturnCode.WS205006.getMsg(), WsReturnCode.WS205006.getCode());
        }
        return jsonObject;
    }

    /**
     * 获取粉丝列表
     *
     * @param access_token
     * @param next_openid  下一个用户openid
     * @return
     * @throws Exception
     */
    public static JSONObject getFansList(String access_token, String next_openid) throws Exception {
        String url = null;
        if (StringUtils.isNotBlank(next_openid)) {
            url = FANS_OPENID_LIST_URL.replace("ACCESS_TOKEN", access_token)
                    .replace("NEXT_OPENID", next_openid);
        } else {
            url = FANS_OPENID_LIST_END_URL.replace("ACCESS_TOKEN", access_token);
        }

        JSONObject jsonObject = HttpsClientUtil.doGet(url);
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            log.error("拉取粉丝openid列表失败，errorCode：" + jsonObject.getString("errcode")
                    + ",errmsg:" + jsonObject.getString("errmsg"));
            throw new WsBizException(WsReturnCode.WS205003.getMsg(), WsReturnCode.WS205003.getCode());
        }
        return jsonObject;
    }

    /**
     * 获取单个粉丝用户信息列表
     *
     * @param access_token
     * @param openid
     * @return
     * @throws Exception
     */
    public static JSONObject getFansInfo(String access_token, String openid) throws Exception {
        String url = USER_INFO.replace("ACCESS_TOKEN", access_token)
                .replace("OPENID", openid);
        log.info("获取个人信息地址为：{}", url);
        JSONObject jsonObject = HttpsClientUtil.doGet(url);
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            log.error("拉取单个粉丝信息失败，errorCode：" + jsonObject.getString("errcode")
                    + ",errmsg:" + jsonObject.getString("errmsg"));
            throw new WsBizException(WsReturnCode.WS205001.getMsg(), WsReturnCode.WS205001.getCode());
        }
        return jsonObject;
    }


    /**
     * 获取批量粉丝用户信息列表
     *
     * @param access_token
     * @param openidsList
     * @return
     * @throws Exception
     */
    public static JSONObject getFansInfoList(String access_token, List<String> openidsList) throws Exception {
        String json = WxMpUserQueryAssembler.assembleToRequestINfoList(openidsList);
        log.info("拉取粉丝信息列表，请求json为：{}", json);
        String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + access_token;
        JSONObject jsonObject = HttpsClientUtil.doPost2(url, json);
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            log.error("拉取粉丝信息列表失败，errorCode：" + jsonObject.getString("errcode")
                    + ",errmsg:" + jsonObject.getString("errmsg"));
            throw new WsBizException(WsReturnCode.WS205003.getMsg(), WsReturnCode.WS205003.getCode());
        }
        return jsonObject;
    }


    /**
     * 创建菜单
     *
     * @param accessToken 调用凭证
     * @param body        实体
     */
    public static void createMenu(String accessToken, String body) throws Exception {
        String url = CREATE_MENU.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url, body);
        log.info("创建菜单返回信息：{}", jsonObject.toJSONString());
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            if (!"0".equals(jsonObject.getString("errcode"))) {
                log.error("创建菜单返回信息，errorCode：" + jsonObject.getString("errcode")
                        + ",errmsg:" + jsonObject.getString("errmsg"));
                throw new WsBizException(WsReturnCode.WS205007.getMsg(), WsReturnCode.WS205007.getCode());
            }
        }
    }

    /**
     * 创建带参数的二维码（临时二维码）
     *
     * @param accessToken
     * @param body
     * @throws Exception
     */
    public static WxQrcodeDto createQrcode(String accessToken, String body) throws Exception {
        String url = CREATE_QRCODE.replace("TOKEN", accessToken);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url, body);
        log.info("返回结果为：{}", jsonObject.toJSONString());
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            log.error("创建带参数二维码信息失败，errorCode：" + jsonObject.getString("errcode")
                    + ",errmsg:" + jsonObject.getString("errmsg"));
            throw new WsBizException(WsReturnCode.WS205008.getMsg(), WsReturnCode.WS205008.getCode());
        }
        WxQrcodeDto wxQrcodeDto = JSONObject.toJavaObject(jsonObject, WxQrcodeDto.class);
        return wxQrcodeDto;
    }

    /**
     * 获取素材列表
     *
     * @param accessToken 调用接口凭证
     * @throws Exception
     */
    public static JSONObject batchgetMaterial(String accessToken, String body) throws Exception {
        String url = BATCHGET_MATERIAL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url, body);
        log.info("获取素材列表返回结果为：{}", jsonObject.toJSONString());
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            log.error("获取素材列表信息失败，errorCode：" + jsonObject.getString("errcode")
                    + ",errmsg:" + jsonObject.getString("errmsg"));
            throw new WsBizException(WsReturnCode.WS205009.getMsg(), WsReturnCode.WS205009.getCode());
        }
        return jsonObject;
    }


    /**
     * 发送客服消息
     *
     * @param accessToken
     * @param jsonString
     * @return
     * @throws Exception
     */
    public static JSONObject sendCustomMessage(String accessToken, String jsonString) throws Exception {
        String url = SEND_CUSTOM_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url, jsonString);
        log.info("发送客服消息返回结果为为：{}", jsonObject.toJSONString());
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            if (!"0".equals(jsonObject.getString("errcode"))) {
                log.error("发送客服消息失败，errorCode：" + jsonObject.getString("errcode")
                        + ",errmsg:" + jsonObject.getString("errmsg"));
            }
        }
        return jsonObject;
    }

    /**
     * 发送模板消息
     *
     * @param accessToken
     * @param wxSendTemplateMessageCmd
     * @return
     * @throws Exception
     */
    public static JSONObject sendTemplateMessage(String accessToken, WxSendTemplateMessageCmd wxSendTemplateMessageCmd) throws Exception {
        String url = SEND_TEMPLATE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url, JSON.toJSONString(wxSendTemplateMessageCmd));
        log.info("发送模板消息返回结果为为：{}", jsonObject.toJSONString());
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {

            if (!"0".equals(jsonObject.getString("errcode"))) {
                log.error("发送模板消息失败，errorCode：" + jsonObject.getString("errcode")
                        + ",errmsg:" + jsonObject.getString("errmsg"));
                throw new WsBizException(WsReturnCode.WS205015.getMsg(), WsReturnCode.WS205015.getCode());
            }
        }
        return jsonObject;
    }


    /**
     * 获取js-sdk中的jsapi_ticket
     *
     * @param accessToken 调用凭证
     * @return
     * @throws Exception
     */
    public static String getJsapiTicket(String accessToken) throws Exception {
        String url = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = HttpsClientUtil.doGet(url);
        log.info("调用获取js-sdk中的jsapi_ticket的地址为{}，返回结果为：{}", url, jsonObject.toJSONString());
        if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
            if (!"0".equals(jsonObject.getString("errcode"))) {
                log.error("获取js-sdk中的jsapi_ticket信息失败，errorCode：" + jsonObject.getString("errcode")
                        + ",errmsg:" + jsonObject.getString("errmsg"));
                throw new WsBizException(WsReturnCode.WS205014.getMsg(), WsReturnCode.WS205014.getCode());
            }
        }
        String ticket = jsonObject.getString("ticket");
        return ticket;
    }

}
