package com.jinshuo.mall.service.wx.utils;

import java.util.Random;

/**
 * 生成随机数nonce
 * @author dongyh
 * @Title: RandomNonceUtils
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/18 11:58
 */
public class RandomNonceUtils {
    public static String getNonce() {
        int length = 8;
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
