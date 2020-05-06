package com.jinshuo.core.exception.user;

/**
 * @Classname UcReturnCode
 * @Description TODO
 * @Date 2019/6/28 15:35
 * @Created by dongyh
 */
public enum UcReturnCode {

    UC200001(203008, "请勿重复收藏！"),
    UC200002(203009, "获取用户信息失败！"),
    UC200003(203010, "根据邀请码获取用户失败！"),
    UC200004(203011, "当前海报用户不存在，请重新生成"),
    UC200005(203013, "获取升级规则错误"),
    UC200006(203014, "获取粉丝列表失败"),
    UC200007(203015, "获取店铺微信配置信息失败"),
    UC200008(203016, "获取粉丝信息列表失败"),
    UC200009(203017, "用户名不存在"),
    UC200010(203018, "密码不正确"),
    UC200011(203019, "登录失败"),
    UC200012(203020, "当前店铺未绑定公众号"),
    UC200013(203021, "自动发卷错误"),
    UC200014(203022, "当前会员等级不符合更新成小麦客"),
    UC200015(203023, "当前会员为大麦客，无需更新"),
    UC200016(203024, "当前会员等级不符合更新成小麦客"),
    UC200017(203025, "手机验证码错误"),
    UC200018(203026, "手机验证码已过期，请重新获取"),
    UC200019(203027, "当前手机和验证码不匹配"),
    UC200020(203028, "验证失败，请刷新页面重试"),
    UC200021(203029, "用户不存在"),
    UC200030(203030, "用户名已经存在!"),
    UC200031(203031, "当前角色不存在"),
    UC200032(203032, "当前模块不存在"),
    UC200033(203033, "当前店铺未开通短信功能"),
    UC200034(203034, "发送短信失败"),
    UC200035(203035, "该手机号已经注册！"),
    UC200036(203036, "角色id不能为空！"),
    UC200037(203037, "角色不存在！"),
    UC200038(203038, "旧支付密码错误"),
    UC200039(203039, "预留手机号码不正确"),
    UC200040(203040, "支付密码错误"),
    UC200041(203041, "请先设置支付密码"),
    UC200042(203042, "店铺不存在"),
    UC200043(203043, "当前数据不存在"),
    UC200044(203044, "版本号格式错误"),
    UC200045(203045, "当前投诉不存在"),
    ;
    private int code;
    private String msg;


    UcReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static UcReturnCode getEnum(int code) {
        for (UcReturnCode ele : UcReturnCode.values()) {
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
