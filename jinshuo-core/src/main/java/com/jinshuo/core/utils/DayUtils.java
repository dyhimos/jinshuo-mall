package com.jinshuo.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 19458 on 2019/8/12.
 */
@Slf4j
public class DayUtils {

    public static Integer mathDay(Date startDay, Date endDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long start = startDay.getTime();
        Long end = endDay.getTime();
        Long days = (end - start) / (1000 * 60 * 60 * 24);
        //运行天数从1开始计数
        Long runningDays = days + 1;
        //判断是否跨天，若跨天，运行天数还要+1
        Long probableEndMillis = start + (1000 * 60 * 60 * 24) * days;
        if (new Date(probableEndMillis).getDay() != new Date(end).getDay()) {
            runningDays++;
        }
        Integer day = Integer.parseInt(runningDays.toString());

        log.info(simpleDateFormat.format(startDay) + "与" + simpleDateFormat.format(endDay) + "相差" + day + "天！");
        return day;
    }

    public static String sdf(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] arg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Long l = date.getTime() - 9998956535l;
        date.setTime(l);
        log.info("aaa"+mathDay(date, new Date()));
    }
}
