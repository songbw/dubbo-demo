package com.flzc.quartz.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {

    public static SimpleDateFormat monthFormatter = new SimpleDateFormat("yyyy-MM");

    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //计算抽奖卷有效时间
    public static Date getEndDate(Date createDate, int month) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(createDate);
        cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) + month);
        return cl.getTime();
    }

    /**
     * 得到过期时间，分钟
     * @return
     */
    public static Date getEndDateForMinute(Date createDate, int minute) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(createDate);
        cl.set(Calendar.MINUTE, cl.get(Calendar.MINUTE) + minute);
        return cl.getTime();
    }

    /**
    * 在原有的日期上加天数
    * @param d
    * @param day
    * @return
    * @throws ParseException
    */
    public static Date addDate(Date d, long day) {

        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);

    }

    /**
     * 计算两个时间相差的天数
     * @param startday
     * @param endday
     * @return
     */
    public static int getIntervalDays(String startday, String endday) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sdate = null;
        Date edate = null;
        try {
            sdate = simple.parse(startday);
            edate = simple.parse(endday);
            //确保startday在endday之前 
            if (sdate.after(edate)) {
                Date cal = sdate;
                sdate = edate;
                edate = cal;
            }
            //分别得到两个时间的毫秒数 
            long sl = sdate.getTime();
            long el = edate.getTime();
            long ei = el - sl;
            //根据毫秒数计算间隔天数 
            return (int) (ei / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 计算两个时间相差的天数
     * @param startday
     * @param endday
     * @return
     */
    public static int getIntervalDays(Date sdate, Date edate) {

        //确保startday在endday之前 
        if (sdate.after(edate)) {
            Date cal = sdate;
            sdate = edate;
            edate = cal;
        }
        //分别得到两个时间的毫秒数 
        long sl = sdate.getTime();
        long el = edate.getTime();
        long ei = el - sl;
        //根据毫秒数计算间隔天数 
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    /**
     * 返回当前的日期字符串 格式： "yy-MM-dd"
     * 
     * @return
     */
    public static String getCurrenDate() {
        String date = dateFormatter.format(new Date());
        return date;
    }

    /**
     * 获得当前月份 yyyy-MM
     * */
    public static String getCurrenMonth(Date date) {
        String month = monthFormatter.format(date);
        return month;
    }

    /**
     * 获得下个月份 yyyy-MM
     * */
    public static String getNextMonth(Date date, int month) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) + month);
        String months = monthFormatter.format(cl.getTime());
        return months;
    }

    /**
     * 获得下个月份 yyyy-MM-dd hh-mm-ss
     * */
    public static String getDate(Date date) {
        String months = dateTimeFormatter.format(date);
        return months;
    }

    /**
     * 得到昨天
     * yyyy-MM-dd
     */
    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        //n为推迟的周数，0本周，-1向前推迟一周，2下周，依次类推
        cal.add(Calendar.DATE, -1);
        return getCurrenDate(cal.getTime());
    }

    /**
     * 得到明天
     * yyyy-MM-dd
     */
    public static Date getTomorrow() {
        Calendar cal = Calendar.getInstance();
        //n为推迟的周数，0本周，-1向前推迟一周，2下周，依次类推
        cal.add(Calendar.DATE, 1);

        return convertStringToDate("yyyy-MM-dd hh:mm:ss", getCurrenDate(cal.getTime()) + " 00:30:00");
    }

    /**
     * 得到昨天
     * yyyy-MM-dd
     */
    public static Date getYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        //n为推迟的周数，0本周，-1向前推迟一周，2下周，依次类推
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
    * 返回当前的日期字符串 格式： "yy-MM-dd"
    * 
    * @return
    */
    public static String getCurrenDate(Date datefrom) {
        String date = dateFormatter.format(datefrom);
        return date;
    }

    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
        }

        return (date);
    }

    /**
     * 获取某月的第一天
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某月的最后1天
     * @param date
     * @return
     */
    public static Date getLastlyDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 得到此周的周1
     * 
     * @param date
     * @return
     */
    public static Date getMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            date = DateUtil.addDays(date, -7);
            c.setTime(date);
        }

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String mondayString = DateUtil.format(c.getTime(), "yyyy-MM-dd");
        mondayString = mondayString + " 00:00:00";
        return formatString(mondayString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getSunday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String mondayString = DateUtil.format(c.getTime(), "yyyy-MM-dd");
        mondayString = mondayString + " 23:59:59";
        return formatString(mondayString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getLastMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_YEAR, -1);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String mondayString = DateUtil.format(c.getTime(), "yyyy-MM-dd");
        mondayString = mondayString + " 00:00:00";
        return formatString(mondayString, "yyyy-MM-dd HH:mm:ss");
    }

    /**
    * 将字符串转化为Date
    * 参数例子：
    * time:2005-05-05
    * format:"yyyy-MM-dd"
    */
    public static Date formatString(String time, String format) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(format);
            return formater.parse(time);
        } catch (Exception e) {
            return null;
        }
    }

    static public final Date addDays(Date target, int days) {
        long msPerDay = 1000 * 60 * 60 * 24; // 一天的毫秒数
        long msTarget = target.getTime(); // 返回从一个特定的日期（1970。。）到现在经过的毫秒数。
        long msSum = msTarget + (msPerDay * days);
        Date result = new Date();
        result.setTime(msSum); // 根据毫秒设置日期
        return result;
    }

    // 获得本周星期日的日期
    public static Date getCurrentWeekday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        return monday;
    }

    // 获得当前日期与本周日相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; //因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static Date convertStringToDate(String strDate) {
        Date aDate = null;
        try {
            SimpleDateFormat sif = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            aDate = sif.parse(strDate);
        } catch (Exception pe) {
        }

        return aDate;
    }
    
    /**
     * subDay(时间减去天数)
     * @author chenqi
     * @date 2014年7月23日 下午2:47:09
     * @param beginDate
     * @param day
     * @return
     */
    public static Date subDay(Date beginDate,int day){
    	
		try {
			
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
			beginDate = dateTimeFormatter.parse(dateTimeFormatter.format(date.getTime()));
			
		} catch (ParseException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beginDate;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public static long calIntervalSec(long start ,long end){
          return    (end - start) /  1000 ;
    }

    public static void main(String[] args) {

	}
}
