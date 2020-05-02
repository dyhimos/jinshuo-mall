//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jinshuo.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    public static final String fm_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String fm_yyyy_MM_dd_HHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FULL_DATE_FORMAT_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String fm_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String fm_yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String fm_yyyyMMdd = "yyyyMMdd";
    public static final String fm_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String fmx_yyyy_MM_dd = "yyyy/MM/dd";
    public static final String fmp_yyyy_MM_dd = "yyyy-MM.dd";
    public static final String fm_MM_dd = "MM.dd";
    public static final String cn_yyyyMMdd = "yyyy年MM月dd日";
    public static final String cn_MMdd = "MM月dd日";

    public DateUtil() {
    }

    public static Date parse(Date cur) {
        if(cur == null) {
            return cur;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(cur);
            cal.set(11, 0);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            return cal.getTime();
        }
    }

    public static Date stirngToDate(String cur, String fm) {
        if(StringUtils.isBlank(cur)) {
            return null;
        } else {
            if(StringUtils.isBlank(fm)) {
                fm = "yyyyMMdd";
            }

            try {
                return (new SimpleDateFormat(fm)).parse(cur);
            } catch (ParseException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static String dateToString(Date cur, String fm) {
        if(cur == null) {
            cur = Calendar.getInstance().getTime();
        }

        if(StringUtils.isBlank(fm)) {
            fm = "yyyyMMdd";
        }

        return (new SimpleDateFormat(fm)).format(cur);
    }

    public static Date getBeginDate(Date cur, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.add(5, -num);
        return c.getTime();
    }

    public static Date getAfterDate(Date cur, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.add(5, num);
        return c.getTime();
    }

    public static Date yesterday(Date cur) {
        return getBeginDate(cur, 1);
    }

    public static Date tomorrow(Date cur) {
        return getAfterDate(cur, 1);
    }

    public static Date firstDayOfMonth(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.add(2, 0);
        c.set(5, 1);
        return c.getTime();
    }

    public static Date firstDayOfLastMonth(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.add(2, -1);
        c.set(5, 1);
        return c.getTime();
    }

    public static Date firstDayOfWeek(Date cur) {
        Calendar cd = Calendar.getInstance();
        cd.setFirstDayOfWeek(2);
        cd.setTime(cur);
        cd.set(7, 2);
        return cd.getTime();
    }

    public static Date firstDayOfLastWeek(Date cur) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(2);
        calendar.setTime(cur);
        calendar.add(3, -1);
        calendar.set(7, 2);
        return calendar.getTime();
    }

    public static Date lastDayOfLastWeek(Date cur) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(2);
        calendar.setTime(cur);
        calendar.set(7, 1);
        return calendar.getTime();
    }

    public static Date curDayOfLastWeek(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.add(3, -1);
        return c.getTime();
    }

    public static Date curDayOflastTwoWeek(Date cur) {
        return curDayOfLastWeek(curDayOfLastWeek(cur));
    }

    public static Date curDayOfLastMonth(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.add(2, -1);
        return c.getTime();
    }

    public static Date curDayOfLastTwoMonth(Date cur) {
        return curDayOfLastMonth(curDayOfLastMonth(cur));
    }

    public static Date curDayOfLastYear(Date cur) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cur);
        cal.add(1, -1);
        return cal.getTime();
    }

    public static Date firstDayOfWeekByLastYear(Date cur) {
        return firstDayOfWeek(curDayOfLastYear(cur));
    }

    public static boolean curDateIsMonday(Date cur) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(cur);
        return cd.get(7) == 2;
    }

    public static int getOfYear(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        return c.get(1);
    }

    public static int getOfMonth(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        return c.get(2) + 1;
    }

    public static int getDayOfMonth(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        return c.get(5);
    }

    public static int getDayOfWeek(Date cur) {
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        c.setFirstDayOfWeek(2);
        return c.get(3);
    }

    public static Date createCustomDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        return c.getTime();
    }

    public static List<Date> getDateRangeList(Date start, Date end) {
        ArrayList<Date> ret = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Date tmpDate = calendar.getTime();

        for(long endTime = end.getTime(); tmpDate.before(end) || tmpDate.getTime() == endTime; tmpDate = calendar.getTime()) {
            ret.add(parse(calendar.getTime()));
            calendar.add(6, 1);
        }

        return ret;
    }

    public static Date englishStringToDate(String cur) throws ParseException {
        try {
            return StringUtils.isBlank(cur)?null:(new SimpleDateFormat("MMM dd, yyyy", Locale.US)).parse(cur);
        } catch (ParseException var2) {
            var2.printStackTrace();
            throw var2;
        }
    }

    public static boolean isBetween(Date cur, Date from, Date to) {
        if(null != cur && null != from && null != to) {
            Calendar _cur = Calendar.getInstance();
            _cur.setTime(cur);
            Calendar c = Calendar.getInstance();
            c.setTime(from);
            int result = _cur.compareTo(c);
            if(result > 0) {
                c.setTime(to);
                result = _cur.compareTo(c);
                if(result < 0) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static Date UTCToCST(Date date, String format) throws ParseException {
        System.out.println("UTC时间: " + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(10, calendar.get(10) + 8);
        System.out.println("北京时间: " + calendar.getTime());
        return calendar.getTime();
    }

    public static Date transForDate(Integer ms) {
        if(ms == null) {
            ms = Integer.valueOf(0);
        }

        long msl = (long)ms.intValue() * 1000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp = null;
        if(ms != null) {
            try {
                String str = sdf.format(Long.valueOf(msl));
                temp = sdf.parse(str);
            } catch (ParseException var6) {
                var6.printStackTrace();
            }
        }

        return temp;
    }

    public static List<Date> getMouthDays(Date date) {
        List<Date> fullDayList = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int year = getOfYear(date);
        int month = getOfMonth(date);
        int day = getDayOfMonth(date);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1, year);
        cal.set(2, month - 1);
        cal.set(5, day);
        int count = cal.getActualMaximum(5);
        int j = 0;

        while(j <= count - 1 && !sdf.format(cal.getTime()).equals(getLastDay(year, month))) {
            cal.add(5, j == 0?0:1);
            ++j;
            fullDayList.add(cal.getTime());
        }

        return fullDayList;
    }

    public static String getLastDay(int year, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month);
        cal.set(5, 0);
        return sdf.format(cal.getTime());
    }

    public static void main(String[] args) {
    }
}
