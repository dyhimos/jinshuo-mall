package com.jinshuo.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 19458 on 2019/12/13.
 */
public class DateUtils {

    public static Date getToday() {
        Calendar calendar = new GregorianCalendar();
        //calendar.add(Calendar.DAY_OF_MONTH, -1);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        return dayStart;
    }

    public static void main(String[] arg) {
        Date date = DateUtils.getToday();
        System.out.print("---");
    }
}
