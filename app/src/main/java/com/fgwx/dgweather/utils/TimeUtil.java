package com.fgwx.dgweather.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：燕青 on 2016/1/11 13:44
 * 邮箱：359222347@qq.com
 */
public class TimeUtil {

    /**
     * 返回日期字符串，包括了星期几
     * 模板:周四  12月31日
     *
     * @param dateStr
     * @return
     */
    public static String strToDateStr(String dateStr) {
        Long time = Long.parseLong(dateStr);
        LogUtil.e("longtime"+time);
        Date date = longToDate(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E  MM月dd日");
        return dateFormat.format(date);
    }

    /**
     * 格式化date
     * 模板:2015-12-31 09:30:53 周四
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS E");
        return dateFormat.format(date);
    }

    /**
     * 格式化date
     * 模板:2015-12-31 09:30:53 周四
     *
     * @param date
     * @return
     */
    public static String formatDate1(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E  MM月dd日");
        return dateFormat.format(date);
    }

    /**
     * 格式化日期
     * 模板:12-31 09:30
     *
     * @param date
     * @return
     */
    public static String formatShortDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return dateFormat.format(date);
    }

    /**
     * long类型的值转成date
     *
     * @param time
     * @return
     */
    public static Date longToDate(long time) {
        return new Date(time);
    }

    /**
     * 字符串类型的时间 转成date
     *
     * @param dateStr 可以转成long的毫秒的字符串
     * @return
     */
    public static Date strToDate(String dateStr) {
        long time = Long.parseLong(dateStr);
        return longToDate(time);
    }
}
