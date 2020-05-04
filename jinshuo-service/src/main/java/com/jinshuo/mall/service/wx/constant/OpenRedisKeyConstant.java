package com.jinshuo.mall.service.wx.constant;

/**
* @Description:    微信缓存redis key 常量
* @Author:         mgh
* @CreateDate:     2019/7/12 17:54
* @UpdateUser:     mgh
* @UpdateDate:     2019/7/12 17:54
* @UpdateRemark:
* @Version:        1.0
*/
public class OpenRedisKeyConstant {

    /**
     * 微信推送到第三方平台的验证票据
     */
    public static final String COMPONENT_VERIFY_TICKET_KEY = "WX_OPEN_TICKET_KEY";

    /**
     * 第三方平台令牌令牌key 每个令牌的有效期为 2 小时
     */
    public static final String COMPONENT_ACCESS_TOKEN_KEY = "WX_OPEN_COMPONENT_ACCESS_TOKEN_KEY";

    /**
     * 第三方平台预授权码 每个预授权码有效期为 10 分钟
     */
    public static final String PRE_AUTH_CODE_KEY = "WX_OPEN_PRE_AUTH_CODE_KEY";

    /**
     * 第三方平台接口调用令牌key 每个令牌的有效期为 2 小时
     */
    public static final String AUTHORIZER_ACCESS_TOKEN_KEY = "WX_OPEN_AUTHORIZER_ACCESS_TOKEN_KEY";


}
