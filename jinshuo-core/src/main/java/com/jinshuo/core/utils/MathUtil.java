package com.jinshuo.core.utils;

import java.math.BigDecimal;

/**
 * 算术计算工具类
 * Created by dyh on 2018/9/12
 */
public class MathUtil {



    /**
     *  提供（相对）精确的加法运算。有空值按0处理，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1        参数1
     * @param v2        参数2
     * @param scale  表示表示需要精确到小数点以后几位。
     * @return  两个参数的和
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if(null == v1){v1 = BigDecimal.valueOf(0.0);}
        if(null == v2){v2 = BigDecimal.valueOf(0.0);}
        return  v1.add(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  提供（相对）精确的减法运算。有空值按0处理，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1        减数
     * @param v2        被减数
     * @param scale  表示表示需要精确到小数点以后几位。
     * @return  两个参数的差
     */
    public static BigDecimal subtract(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if(null == v1){v1 = BigDecimal.valueOf(0.0);}
        if(null == v2){v2 = BigDecimal.valueOf(0.0);}
        return  v1.subtract(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     *  提供（相对）精确的乘法运算。有空值按0处理，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1        参数1
     * @param v2        参数2
     * @param scale  表示表示需要精确到小数点以后几位。
     * @return  两个参数的和
     */
    public static BigDecimal multiply(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if(null == v1){v1 = BigDecimal.valueOf(0.0);}
        if(null == v2){v2 = BigDecimal.valueOf(0.0);}
        return  v1.multiply(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }



    /***
     * 比较大小
     * -1、0、1，即左边比右边数大，返回1，相等返回0，比右边小返回-1。
     * @param v1  参数1
     * @param v2  参数2
     * @return int
     */
    public static int compareTo(BigDecimal v1,BigDecimal v2){
        if(null == v1){v1 = BigDecimal.valueOf(0.0);}
        if(null == v2){v2 = BigDecimal.valueOf(0.0);}
        return  v1.compareTo(v2);
    }


    /**
     * a / b   计算百分比
     * @param a
     * @param b
     * @return eg:65.32%
     */
    public static String ADivideBPercent(BigDecimal a,BigDecimal b){
        String percent =
                b == null ? "-" :
                        b.compareTo(new BigDecimal(0)) == 0 ? "-":
                                a == null ? "0.00%" :
                                        a.multiply(new BigDecimal(100)).divide(b,2,BigDecimal.ROUND_HALF_UP) + "%";
        return percent;
    }

}
