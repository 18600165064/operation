package com.data.display.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: CYN
 * @Date: 2018/12/20 17:32
 * @Description:时间工具类
 */
public class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH:00:00";
    /**
     * 获取剩余秒数
     *
     * @param endDate 时间
     * @return
     */

    public static long remainingSeconds(LocalDateTime endDate) {
	    LocalDateTime now = LocalDateTime.now();
	    Duration duration = Duration.between(now, endDate);
	    return duration.getSeconds()<=0?0:duration.getSeconds();
	}
    
    /**
     * localDate转Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant1 = zonedDateTime.toInstant();
        Date from = Date.from(instant1);
        return from;
    }

    /**
     * Date 转 localDate
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }

    /**
     * 获取月第一天
     */
    public static Date getStartDayOfMonth(String date) {
        LocalDate now = LocalDate.parse(date);
        return getStartDayOfMonth(now);
    }

    public static Date getStartDayOfMonth(LocalDate date) {
        LocalDate now = date.with(TemporalAdjusters.firstDayOfMonth());
        return localDate2Date(now);
    }

    /**
     * 获取月最后一天
     */
    public static Date getEndDayOfMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return getEndDayOfMonth(localDate);
    }

    public static Date getEndDayOfMonth(LocalDate date) {
        LocalDate now = date.with(TemporalAdjusters.lastDayOfMonth());

        Date.from(now.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
        return localDate2Date(now);
    }

    /**
     * 比较小时
     *
     * @param dateTime 日期
     * @return boolean
     */
    public static boolean comparDateTimeHour(String dateTime, int hour) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
        if (hour == localDateTime.getHour()) {
            return true;
        }
        return false;
    }

    /**
     * 比较日期
     * today 2019-01-17
     * System.out.println(compareDate("2019-01-16")); false
     * System.out.println(compareDate("2019-01-17")); true
     * System.out.println(compareDate("2019-01-18")); true
     *
     * @param date 日期
     * @return boolean
     */
    public static boolean compareDate(String date) {
        LocalDate beginDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(YYYY_MM_DD));
        LocalDate localDate = LocalDate.now();
        if (!localDate.isBefore(beginDate)) {
            return localDate.isEqual(beginDate);
        }
        return localDate.isBefore(beginDate);
    }

    /**
     * 获取当前日期
     *
     * @param type 格式化类型
     * @return cboolean
     */
    public static String getToday(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(new Date());
    }

    /**
     * 获取前某天日期并格式化
     *
     * @param day  天
     * @param type 格式化类型
     * @return String
     */
    public static String getYesterdayFormatte(int day, String type) {
        LocalDate localDate = LocalDate.now().minusDays(day);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(type);
        return dateTimeFormatter.format(localDate);
    }

    /**
     * 获取前某天日期
     *
     * @param day 天
     */
    public static String getBeforeYesterday(int day) {
        return LocalDate.now().minusDays(day).toString();
    }

    /**
     * string日期转date类型
     *
     * @param date 日期
     * @param type 格式化类型
     * @return Date
     * @throws ParseException 转换异常
     */
    public static Date stringToDate(String date, String type) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.parse(date);
    }

    /**
     * date转String类型
     *
     * @param date 日期
     * @param type 格式化类型
     * @return String
     */
    public static String dateToString(Date date, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(date);
    }

    /**
     * 获取两个日期间隔的所有日期
     *
     * @param start 格式必须为'2018-01-25'
     * @param end   格式必须为'2018-01-25'
     * @return
     */
    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }

    /**
     * 获取当天开始时间
     * @return
     */
    public static Date getStartTime() {  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();  
    }  
  
    /**
     * 获取当天结束时间
     * @return
     */
    public static Date getEndTime() {  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime();  
    }
    
    /**
     * 获取 day(负数)  天前的 开始日期
     * @return
     */
    public static Date getStartTime2(int day) {  
        Calendar todayStart = Calendar.getInstance(); 
        todayStart.add(Calendar.DATE,day);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();  
    }

    /**
     * 获取 day(负数)  天前的 结束日期
     * @return
     */
    public static Date getEndTime2(int day) {  
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.add(Calendar.DATE,day);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime();  
    }


    /**
     * 根据某个时间获取这个时间前 day 天的 结束日期
     */
    public static Date getEndTimeByTime(Date date,int day) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.add(Calendar.DATE,day);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }


    /**
     * 当前时间增加 n 小时
     * @param hour
     * @return
     */
    public static Date getAddHour(int hour){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, hour);// 24小时制
        return cal.getTime();
    }

    /**
     * 当前时间增加 n 分钟
     * @param minute
     * @return
     */
    public static Date getAddMinute(int minute){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE,minute);
        return cal.getTime();
    }

    /**
     * 当前时间增加 n 天
     * @param day
     * @return
     */
    public static Date getAddDay(int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, day);// 24小时制
        return cal.getTime();
    }


    /**
     * 当前月增加 n 月
     * @param month
     * @return
     */
    public static Date getAddMonth(int month){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    public static void main(String[] args) {
        try {
            String return_msg = "支付失败";
            String err_code_des = "支付失败";
            String msg = StringUtil.isBlank(return_msg) ? err_code_des : return_msg;
            System.out.println(msg);
        }catch (Exception e){

        }



    }

}
