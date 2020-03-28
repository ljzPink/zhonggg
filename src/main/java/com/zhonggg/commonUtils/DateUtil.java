package com.zhonggg.commonUtils;




import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang.time.DateUtils.parseDate;

/**
 * 日期时间工具类
 *
 * @author wangxiaolong
 * @date 2019/6/24.
 */
public class DateUtil {
    public static final String DEFAULT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME_NULL = "yyyyMMddHHmmss";
    public static final String DEFAULT_DATETIME_S = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DEFAULT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_MONTH = "yyyy-MM";


    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentTime() {
        return getCalendar().getTime();
    }

    public static Calendar getCalendar(String strDate) {
        Calendar calendar = getCalendar();
        try {
            calendar.setTime(parse(strDate));
        } catch (ParseException e) {
        }
        return calendar;
    }

    public static Calendar getCalendar(String strDate, String format) {
        Calendar calendar = getCalendar();
        try {
            calendar.setTime(parse(strDate, format));
        } catch (ParseException e) {
        }
        return calendar;
    }

    /**
     * Date转字符串
     *
     * @author wangxiaolong
     * @date 2019/11/20 17:20
     */
    public static String format(Date date) {
        return format(date, DEFAULT_DATETIME);
    }

    public static String format(long date) {
        return format(date, DEFAULT_DATETIME);
    }

    public static String format(Calendar date) {
        return format(date, DEFAULT_DATETIME);
    }

    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    public static String format(long date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    public static String format(Calendar date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 字符串转Date
     *
     * @author wangxiaolong
     * @date 2019/11/20 17:20
     */
    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, DEFAULT_DATETIME);
    }

    public static Date parse(String strDate, String pattern) throws ParseException {
        return parseDate(strDate, new String[]{pattern});
    }

    /**
     * 转换成星期
     *
     * @author wangxiaolong
     * @date 2019/11/20 16:26
     */
    public static Integer getWeekDay(String datetime, String pattern) {
        Calendar calendar = getCalendar();
        try {
            calendar.setTime(parse(datetime, pattern));
        } catch (ParseException e) {
            return 0;
        }
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return day % Calendar.DAY_OF_WEEK == 0 ? Calendar.DAY_OF_WEEK : day;
    }

    /**
     * 当前日期加多少天
     *
     * @author wangxiaolong
     * @date 2019/11/20 16:27
     */
    public static Date forwardDay(Integer dayCount) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DATE, dayCount);
        return calendar.getTime();
    }

    public static Date forward(Integer dateType, Integer count) {
        Calendar calendar = getCalendar();
        calendar.add(dateType, count);
        return calendar.getTime();
    }

    /**
     * 时间差
     *
     * @author wangxiaolong
     * @date 2019/11/20 16:27
     */
    public static Integer timeDifference(Date startTime, Date endTime, int type) {
        Calendar startCalendar = getCalendar();
        startCalendar.setTime(startTime);
        Calendar endCalendar = getCalendar();
        endCalendar.setTime(endTime);
        return endCalendar.get(type) - startCalendar.get(type);
    }

    /**
     * 获取时间的 年月日时分秒
     *
     * @author wangxiaolong
     * @date 2019/11/20 16:27
     */
    public static Integer getValueByType(Date time, int type) {
        Calendar calendar = getCalendar();
        calendar.setTime(time);
        return calendar.get(type);
    }

    /**
     * 判断时间是否当天
     */
    public static boolean isToday(Date date) {
        return format(getCalendar(), DEFAULT_DATE).equals(format(date, DEFAULT_DATE));
    }

    /**
     * 转换成固定格式的日期
     */
    public static Date convertData(Date date, String pattern) {
        try {
            return parse(format(date, pattern), pattern);
        } catch (ParseException e) {
            return date;
        }
    }
}
