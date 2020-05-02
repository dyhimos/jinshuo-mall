package com.jinshuo.core.utils;

import java.util.Random;

/**
 * Created by 19458 on 2019/11/19.
 */
public class VerificationCodeUtils {


    /**
     * 生成优惠券
     *
     * @return
     */
    public static String getCode() {
        return getNumRandom(12);
    }


    public static String getNumRandom(Integer n) {
        if (null == n || 0 > n) {
            n = 8;
        }
        StringBuilder str = new StringBuilder();//定义变长字符串
        Random random = new Random();
        //随机生成数字，并添加到字符串
        for (int i = 0; i < n; i++) {
            str.append(random.nextInt(10));
        }
        //将字符串转换为数字并输出
        return str.toString();
    }
}
