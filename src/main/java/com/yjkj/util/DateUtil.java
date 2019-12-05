package com.yjkj.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bin on 2017/11/7.
 */
public class DateUtil {
    public static void main(String[] args) {
        /*List<String> tList=getTimeByDay();
        for (String t:tList){
            System.out.println(t);
        }*/
        System.out.println(getMonthStr(-1));
        System.out.println(getMonthStr(-7));

        List<String> tList=getTimeByMonth();
        for (String t:tList){
            System.out.println(t);
        }
    }
    /**
     * 获取指定时间戳所在月份开始的时间戳/秒
     * @param dateTimeMillis 指定时间戳/毫秒
     * @return
     */
    public static Long getMonthBegin(Long dateTimeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(dateTimeMillis));

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis() / 1000;
    }

    /**
     * 获取指定时间戳所在月份15号的时间戳/秒
     * @param dateTimeMillis 指定时间戳/毫秒
     * @return
     */
    public static Long getMonthMiddle(Long dateTimeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(dateTimeMillis));

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, 15);
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND,59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis() / 1000;
    }

    /**
     * 获取指定时间戳所在月份结束的时间戳/秒
     * @param dateTimeMillis 指定时间戳/毫秒
     * @return
     */
    public static Long getMonthEnd(Long dateTimeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(dateTimeMillis));

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND,59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis() / 1000;
    }


    public static boolean payTime(String aisleCode){
        //TODO aisleCode
        if ("ybq".equals(aisleCode)){
            long nowTime=new Date().getTime();
            long zeroTime= DateUtil.zero(nowTime);
            return nowTime>zeroTime+23*60*60*1000L+20*60*1000L||nowTime<zeroTime+8*60*60*1000L;
        }else {
            long nowTime=new Date().getTime();
            long zeroTime= DateUtil.zero(nowTime);
            return nowTime>zeroTime+19*60*60*1000L+50*60*1000L||nowTime<zeroTime+8*60*60*1000L;
        }

    }
    public static String helipayNewDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    public static String newDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmssSSS");
        return sdf.format(new Date());
    }
    public static String fmtDate(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }
    public static String longToString(long time,String fmt){
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(new Date(time));
    }
    public static Long zero(Long timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        return start.getTime();
    }
    // 时间戳计算到每天0点    判断有没有当前时间之前的时间戳
    public static boolean testTimestampStr(String[] timeStampStrs){
        Long today9Time=zero(new Date().getTime())+3600*9*1000L;
        for(int i=0;i<timeStampStrs.length;i++){
            if(Long.parseLong(timeStampStrs[i])<today9Time)return false;
        }
        return true;
    }
    public static String[]  fmtTimestampStr(String timeStampStr){
        String [] timeStampStrs=timeStampStr.split(",");
        Arrays.sort(timeStampStrs);
        for(int i=0;i<timeStampStrs.length;i++){
            timeStampStrs[i]=""+zero(Long.parseLong(timeStampStrs[i]));
        }
        return timeStampStrs;
    }
    //计算今天离还款日还有几天
    public static int date(String startDay,String endDay){
        int value;
        int start=Integer.parseInt(startDay);
        int end=Integer.parseInt(endDay);
        if(end>start){
            int v=end-getNowDay();
            //end>getNowDay()
            if(v>0){
                value=v;
            }else {
                value=getCurrentMonthDay()-getNowDay()+end;
            }
        }else {
            int c=end-getNowDay();
            if(c>0){
                value=c;
            }else {
                value=getCurrentMonthDay()-getNowDay()+end;
            }
        }
        return value;
    }
    //上月天数
    public static int lastDay() {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String lastDay = format.format(calendar2.getTime());
        int day = Integer.parseInt(lastDay);
        return day;
    }
    //当月天数
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    public static int getNowDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }

    //是否在系统还款时间段
    public static boolean repaymentTime(){
        Long newTime=new Date().getTime();
        Long zero=zero(newTime);
        Long time_10_30=zero+(10*60+30)*60*1000L;
        Long time_15=zero+15*60*60*1000L;
        if(newTime>time_10_30&&newTime<time_15){
            return false;
        }
        Long time_17_30=zero+(17*60+30)*60*1000L;
        Long time_22=zero+22*60*60*1000L;
        if(newTime>time_17_30&&newTime<time_22){
            return false;
        }
        Long time_22_30=zero+(22*60+30)*60*1000L;
        Long time_0_30=zero+24*60*60*1000L+30*60*1000L;
        if(newTime>time_22_30&&newTime<time_0_30){
            return false;
        }
        return true;
    }
    //每天时间戳
    public static List<String> getTimeByDay(){
        List<String> timeList=new ArrayList<>();
        Long z=zero(new Date().getTime());
        for (int i=0;i<7;i++){
            Long zn=z-(i+1)*24*60*60*1000L;
            timeList.add(fmtDate(zn).substring(0,10));
        }
        return timeList;
    }
    //每月时间戳
    public static List<String> getTimeByMonth(){
        List<String> timeList=new ArrayList<>();
        GregorianCalendar now = new GregorianCalendar();
        SimpleDateFormat fmtrq = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = DateFormat.getDateInstance();
        for (int i=0;i<6;i++){
            now.add(GregorianCalendar.MONTH,-1);//可以是天数或月数  数字自定 -6前6个月
            timeList.add(fmtrq.format(now.getTime()).substring(0,7));
        }
        return timeList;
    }
    public static String getMonthStr(int n){
        GregorianCalendar now = new GregorianCalendar();
        SimpleDateFormat fmtrq = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = DateFormat.getDateInstance();
        now.add(GregorianCalendar.MONTH,n);//可以是天数或月数  数字自定 -6前6个月
        return fmtrq.format(now.getTime()).substring(0,7);
    }
    /**
     * 获取当前时间往后推迟多少天
     * @param date
     * @param d
     * @return
     */
    public static Date getNextDay(Date date, int d){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, d);
        return calendar.getTime();
    }

    /**
     * 获取当前时间往后推迟多分钟
     * @param date
     * @param d
     * @return
     */
    public static Date getNextMinute(Date date, int d){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, d);
        return calendar.getTime();
    }

}
