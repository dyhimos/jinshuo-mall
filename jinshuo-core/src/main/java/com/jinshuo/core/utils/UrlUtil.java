package com.jinshuo.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by 19458 on 2019/8/9.
 */
public class UrlUtil {


    public static String getUrl(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        //return URL2 + str;
        return  str;
    }
}
