package com.jinshuo.core.utils;

/**
 * @author dongyh
 * @Title: GeetestConfig
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/10/31 11:14
 */
public class GeetestConfig {
    // 填入自己的captcha_id和private_key
    private static final String geetest_id = "78e4b059011b5d04e99ce75435ba5e80";
    private static final String geetest_key = "c2fcf4b375ee9dff46238d556f8dc34d";
    private static final boolean newfailback = true;

    public static final String getGeetest_id() {
        return geetest_id;
    }

    public static final String getGeetest_key() {
        return geetest_key;
    }

    public static final boolean isnewfailback() {
        return newfailback;
    }
}
