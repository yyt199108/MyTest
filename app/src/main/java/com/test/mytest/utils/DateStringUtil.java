package com.test.mytest.utils;

import com.blankj.utilcode.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 2017-12-27.
 */

public class DateStringUtil {
    public static String getInterval(Date createAt) {
        // 定义最终返回的结果字符串。
        String interval = null;

        long millisecond = new Date().getTime() - createAt.getTime();

        long second = millisecond / 1000;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int nowHour = cal.get(Calendar.HOUR_OF_DAY);
        int nowMinute = cal.get(Calendar.MINUTE);
        int nowSecond = cal.get(Calendar.SECOND);
        //从凌晨到现在的秒数
        long todaySecond = nowHour*60*60 + nowMinute*60 + nowSecond;

        if (second <= 0) {
            second = 0;
        }

//*--------------微博体（标准）
        if (second == 0) {
            interval = "刚刚";
        } else if (second < 30) {
            interval = second + "秒以前";
        } else if (second >= 30 && second < 60) {
            interval = "半分钟前";
        } else if (second >= 60 && second < 60 * 60) {//大于1分钟 小于1小时
            long minute = second / 60;
            interval = minute + "分钟前";
        } else if (second >= 60 * 60 && second < 60 * 60 * 3) {//大于1小时 小于24小时
            long hour = (second / 60) / 60;
            interval = hour + "小时前";
        } else if(second<todaySecond){//今天
            //今天到目前为止的毫秒数与这24小时内的毫秒数比较，是否在今天的范围内
            interval = "今天" + getFormatTime(createAt, "HH:mm");
        }else if (second <= 60 * 60 * 24 +todaySecond) {//昨天
            interval = "昨天" + getFormatTime(createAt, "HH:mm");
        } else if (second <= 60 * 60 * 24 * 6+todaySecond) {//大于2D 小于 7天
            long day = (((second-todaySecond) / 60) / 60) / 24+1;
            interval = day + "天前";
        } else if ( second <= 60 * 60 * 24 * 365) {//大于7天小于365天
            interval = getFormatTime(createAt, "MM-dd HH:mm");
        } else if (second >= 60 * 60 * 24 * 365) {//大于365天
            interval = getFormatTime(createAt, "yyyy-MM-dd HH:mm");
        } else {
            interval = "0";
        }
        return interval;
    }

    //时间显示策略
    public static String getIntervalShort(Date createAt) {
        // 定义最终返回的结果字符串。
        String interval = null;

        long millisecond = new Date().getTime() - createAt.getTime();

        long second = millisecond / 1000;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int nowHour = cal.get(Calendar.HOUR_OF_DAY);
        int nowMinute = cal.get(Calendar.MINUTE);
        int nowSecond = cal.get(Calendar.SECOND);
        //从凌晨到现在的秒数
        long todaySecond = nowHour*60*60 + nowMinute*60 + nowSecond;

        if (second <= 0) {
            second = 0;
        }

//*--------------微博体（标准）
        if (second >= 0 && second<5*60) {
            interval = "刚刚";
        }else if (second >= 5*60 && second < 60 * 60) {//大于1分钟 小于1小时
            long minute = second / 60;
            interval = minute + "分钟前";
        } else if (second >= 60 * 60 && second < 60 * 60 * 24) {//大于1小时 小于24小时
            long hour = (second / 60) / 60;
            interval = hour + "小时前";
        } else if(second<60*60*24*30){//24小时（一天）至30天
            long day = ((second/ 60) / 60) / 24;
            interval = day + "天前";
        } else if(second >= 60 * 60 * 24 * 30) {//30天以上
            long month = ((second/ 60) / 60) / 24 / 30;
            interval = month + "月前";
        } else {
            interval = "0";
        }
        return interval;
    }

    private static String getFormatTime(Date createAt, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return format.format(createAt);
    }
}
