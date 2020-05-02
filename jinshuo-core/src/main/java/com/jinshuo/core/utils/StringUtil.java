package com.jinshuo.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Classname StringUtil
 * @Description TODO
 * @Date 2019/6/18 10:12
 * @Created by dyh
 */
public class StringUtil extends StringUtils {
    /**
     * 隐藏手机号 中间六位
     * @param phone
     * @return
     */
    public static String hidePhone(String phone) {
        return substring(phone, 0, 3) + "******" + substring(phone, phone.length()-2, phone.length());
    }
}
