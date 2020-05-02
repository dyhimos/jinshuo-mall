package com.jinshuo.core.utils;

import lombok.Data;

import java.util.Random;

/**
 * Created by 19458 on 2019/8/27.
 */
@Data
public class RandomUtil {

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
