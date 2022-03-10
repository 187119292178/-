package com.aishangrun.aishangrun.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/12/21 22:14:58
 * Email: 1095737364@qq.com
 */
public class CalendarDataUtil {

    //返回下一月: 月卡
    public static Date getNextMonth( Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) +1); // 设置为上一个月
        return calendar.getTime();
    }

    //返回下一年: 年卡
    public static Date getNextYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) +1); // 设置为上一个月
        return calendar.getTime();
    }

    public static Date getNextOneHundredYear(  Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) +100); // 设置为上一个月
        return calendar.getTime();
    }
    
    public static Date getNextTenYear(  Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) +10); // 下个十年
        return calendar.getTime();
    }

    //返回下一周
    public static Date  getFetureDate( Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
        Date today = calendar.getTime();
        return today;
    }



//    一个季三个月
    public static Date getThreeMonth( Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) +3); // 设置为上一个月
        return calendar.getTime();
    }


    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date date = new Date();
        System.out.println("当前的时间是：" + dateFormat.format(date));
        System.out.println("当前的时间是：" + dateFormat.format(CalendarDataUtil.getFetureDate(CalendarDataUtil.getNextMonth(date))));
        System.out.println("下月的时间是：" + dateFormat.format(CalendarDataUtil.getNextMonth(date)));
        System.out.println("下三个月的时间是：" + dateFormat.format(CalendarDataUtil.getThreeMonth(date)));
        System.out.println("下年的时间是：" + dateFormat.format(CalendarDataUtil.getNextYear(date)));
        System.out.println("下100年的时间是：" + dateFormat.format(CalendarDataUtil.getNextOneHundredYear(date)));
    }
}