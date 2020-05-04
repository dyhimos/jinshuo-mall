package com.jinshuo.mall.service.wx.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.utils.HttpsClientUtil;
import com.jinshuo.core.utils.RedisUtil;
import com.jinshuo.mall.service.wx.application.cmd.WxMiniCmd;
import com.jinshuo.mall.service.wx.application.cmd.WxMiniCodeCmd;
import com.jinshuo.mall.service.wx.application.cmd.WxMiniSubscribeSendCmd;
import com.jinshuo.mall.service.wx.application.dto.MiniLoginDto;
import com.jinshuo.mall.service.wx.constant.WxMiniRedisKeyConstant;
import com.jinshuo.mall.service.wx.exception.WsBizException;
import com.jinshuo.mall.service.wx.exception.WsReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

/**
 * 微信小程序接口
 * @author dongyh
 * @Title: WxMini
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 10:16
 */
@Slf4j
public class WxMini {

    /**
     * 登录
     */
    public static final String JSCODE2SESSION_URL ="https://api.weixin.qq.com/sns/jscode2session?appid=APPID" +
            "&secret=SECRET" +
            "&js_code=JSCODE" +
            "&grant_type=authorization_code";

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     */
    public static final String TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    /**
     * 获取小程序码，适用于需要的码数量极多的业务场景。通过该接口生成的小程序码，永久有效，数量暂无限制。
     */
    public static final String GETWXACODEUNLIMIT_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";


    /**
     * 订阅消息推送
     */
    public static final String SUBSCRIBE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";



    /**
     * 获取小程序的openid
     * @param wxMiniCmd 小程序构造参数
     * @return
     */
    public static MiniLoginDto getLoginObject(WxMiniCmd wxMiniCmd) throws Exception {
        String url =JSCODE2SESSION_URL.replace("APPID",wxMiniCmd.getAppid())
                .replace("SECRET",wxMiniCmd.getSecret())
                .replace("JSCODE",wxMiniCmd.getCode());
        JSONObject jsonObject = HttpsClientUtil.doGet(url);

        MiniLoginDto miniLoginDto = JSONObject.toJavaObject(jsonObject,MiniLoginDto.class);
        return miniLoginDto;
    }

    /**
     * 解密用户敏感数据获取用户信息
     * @param sessionKey
     *            数据进行加密签名的密钥
     * @param encryptedData
     *            包括敏感数据在内的完整用户信息的加密数据
     * @param iv
     *            加密算法的初始向量
     * @return
     */
    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0)
            {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0)
            {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取小程序access_token
     * @param appid
     * @param secret
     * @return
     * @throws Exception
     */
    public static String getToken(String appid,String secret) throws Exception{
        String url = TOKEN_URL.replaceFirst("APPID",appid)
                .replaceFirst("APPSECRET",secret);
        String token = RedisUtil.getStr(WxMiniRedisKeyConstant.WX_MINI_ACCESS_TOKEN_KEY + appid);
        log.info("从redis中获取小程序的："+token);
        //如果为空，则重新调接口获取，并保存到redis中
        if(StringUtils.isBlank(token)){
            JSONObject jsonObject = HttpsClientUtil.doGet(url);
            String errcode = jsonObject.getString("errcode");
            //如果返回码不会空
            if(StringUtils.isNotBlank(errcode)){
                if(!"0".equals(errcode)){
                    log.error("获取小程序accessToken失败，errorCode：{} ,errmsg:{}",errcode
                            ,jsonObject.getString("errmsg"));
                    throw new WsBizException(WsReturnCode.WS205016.getMsg(),WsReturnCode.WS205016.getCode());
                }
            }
            token = jsonObject.getString("access_token");
            RedisUtil.del(WxMiniRedisKeyConstant.WX_MINI_ACCESS_TOKEN_KEY+ appid);
            log.info("重新获取到的token为："+token);
            //将新的access_token存入redis中
            RedisUtil.setex(WxMiniRedisKeyConstant.WX_MINI_ACCESS_TOKEN_KEY+ appid + appid,token,5400);
        }
        return token;
    }


    /**
     * 生成小程序码,数量无限制
     * @param wxMiniCodeCmd 小程序所需参数
     */
    public String getwxacodeunlimit(WxMiniCodeCmd wxMiniCodeCmd) throws Exception {
        String url = GETWXACODEUNLIMIT_URL.replaceFirst("ACCESS_TOKEN",wxMiniCodeCmd.getAccess_token());
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(wxMiniCodeCmd));
        //如果返回码不会空
        String errcode = jsonObject.getString("errcode");
        if(StringUtils.isNotBlank(errcode)){
            if(!"0".equals(errcode)){
                log.error("生成小程序码失败，errorCode：{} ,errmsg:{}",errcode
                        ,jsonObject.getString("errmsg"));
                throw new WsBizException(WsReturnCode.WS205017.getMsg(),WsReturnCode.WS205017.getCode());
            }
        }

//        {
//                "errcode": 0,
//                "errmsg": "ok",
//                "contentType": "image/jpeg",
//                "buffer": Buffer
//        }
        return jsonObject.getString("buffer");
    }


    /**
     * 小程序发送推送消息
     * @param access_token 接口调用凭证
     * @param wxMiniSubscribeSendCmd 消息所需参数
     * @return
     */
    public void subscribeSend(String access_token,WxMiniSubscribeSendCmd wxMiniSubscribeSendCmd) throws Exception {
        String url = SUBSCRIBE_SEND_URL.replaceFirst("ACCESS_TOKEN",access_token);
        JSONObject jsonObject = HttpsClientUtil.doPost2(url,JSON.toJSONString(wxMiniSubscribeSendCmd));
        //如果返回码不会空
        String errcode = jsonObject.getString("errcode");
        if(StringUtils.isNotBlank(errcode)){
            if(!"0".equals(errcode)){
                log.error("推送小程序消息失败，errorCode：{} ,errmsg:{}",errcode
                        ,jsonObject.getString("errmsg"));
                throw new WsBizException(WsReturnCode.WS205018.getMsg(),WsReturnCode.WS205018.getCode());
            }
        }
    }
}
