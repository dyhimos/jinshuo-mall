package com.jinshuo.mall.service.wx.exception;

/**
 * @Classname UcReturnCode
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by mgh
 */
public enum WsReturnCode {

    WS205001(205001, "获取用户信息失败！"),
    WS205002(205002, "获取粉丝列表openid失败"),
    WS205003(205003, "获取粉丝信息列表失败"),
    WS205004(205004, "获取access_token失败"),
    WS205005(205005, "获取网页授权access_token失败"),
    WS205006(205006, "获取网页授权用户信息失败"),
    WS205007(205007, "创建菜单失败"),
    WS205008(205008, "创建带参数二维码信息失败"),
    WS205009(205009, "获取素材列表失败"),
    WS205010(205010, "获取第三方平台预售码失败"),
    WS205011(205011, "获取第三方平台令牌失败"),
    WS205012(205012, "获取第三方平台授权信息失败"),
    WS205013(205013, "获取第三方平台获取/刷新接口调用令牌失败"),
    WS205014(205014, "获取JS-SDK jsapi_ticket失败"),
    WS205015(205015, "发送模板消息失败"),
    WS205016(205016, "获取小程序acceess_token失败"),
    WS205017(205017, "小程序码生成失败"),
    WS205018(205018, "推送小程序订阅消息失败"),
    WS205019(205019, "获取小程序基本信息失败"),
    WS205020(205020, "设置服务器域名失败"),
    WS205021(205021, "设置业务域名失败"),
    WS205022(205022, "绑定体验者失败"),
    WS205023(205023, "解除绑定体验者失败"),
    WS205024(205024, "上传小程序代码失败"),
    WS205025(205025, "获取小程序体验版二维码失败"),
    WS205026(205026, "提交审核失败"),
    WS205027(205027, "查询最新一次提交的审核状态失败"),
    WS205028(205028, "发布已通过审核的小程序失败"),
    ;
    private int code;
    private String msg;


    WsReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static WsReturnCode getEnum(int code) {
        for (WsReturnCode ele : WsReturnCode.values()) {
            if (ele.getCode() == code) {
                return ele;
            }
        }
        return null;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
