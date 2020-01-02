package com.example.myframe.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sam
 * @version 1.0
 * @Description : 日期时间工具类，进行各种日期时间格式的转化以及格式化
 * @created 2011-9-25 上午09:51:35
 * @fileName com.chcdudu.common.util.DateTimeUtil.java
 */
public class DateTimeUtil {

    // /
    // 定义时间日期显示格式
    // /
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    private final static String DATE_FORMAT_CN = "yyyy年MM月dd日";

    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";

    private final static String YEAR_FORMAT_CN = "yyyy年";

    private final static String MONTH_FORMAT = "yyyy-MM";

    private final static String DAY_FORMAT = "yyyyMMdd";

    private final static String LONG_FORMAT = "yyyyMMddHHmmss";

    /**
     * 取得当前系统时间，返回java.util.Date类型
     *
     * @return java.util.Date 返回服务器当前系统时间
     * @see Date
     */
    public static Date getCurrDate() {
        return new Date();
    }

    /**
     * 取得当前系统时间戳
     *
     * @return java.sql.Timestamp 系统时间戳
     * @see java.sql.Timestamp
     */
    public static java.sql.Timestamp getCurrTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatDate(Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @param currDate 要格式化的日期
     * @return Date 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
     * @throws ParseException
     * @see #getFormatDate(Date)
     */
    public static Date getFormatDateToDate(Date currDate) {
        return getFormatDate(getFormatDate(currDate));
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatDate_CN(Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @param currDate 要格式化的日期
     * @return Date 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     * @throws ParseException
     * @see #getFormatDate_CN(String)
     */
    public static Date getFormatDateToDate_CN(Date currDate) {
        return getFormatDate_CN(getFormatDate_CN(currDate));
    }

    /**
     * 得到格式化后的日期，格式为yyyy年，如2006年
     *
     * @param currDate 要格式化的日期
     * @return Date 返回格式化后的日期，默认格式为yyyy年，如2006年
     * @throws ParseException
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatYear_CN(Date currDate) {
        return getFormatDate(currDate, YEAR_FORMAT_CN);
    }

    /**
     * 得到格式化后的日期，格式为yyyy年，如2006年
     *
     * @param currDate 要格式化的日期
     * @return Date 返回格式化后的日期，默认格式为yyyy年，如2006年
     * @throws ParseException
     * @see #getFormatYear_CN(String)
     */
    public static Date getFormatDateToYear_CN(Date currDate) {
        return getFormatDate_CN(getFormatYear_CN(currDate));
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @param currDate 要格式化的日期
     * @return Date 返回格式化后的日期，默认格式为yyyy-MM-dd，如2006-02-15
     * @throws ParseException
     * @see #getFormatDate(String, String)
     */
    public static Date getFormatDate(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @param currDate 要格式化的日期
     * @return 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     * @throws ParseException
     * @see #getFormatDate(String, String)
     */
    public static Date getFormatDate_CN(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate 要格式化的日期
     * @param format   日期格式，如yyyy-MM-dd
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     * 定义，如yyyy-MM-dd，如2006-02-15
     * @see SimpleDateFormat#format(Date)
     */
    public static String getFormatDate(Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);

            return dtFormatdB.format(currDate);
        }
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate 要格式化的时间
     * @return String 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * @see #getFormatDateTime(Date, String)
     */
    public static String getFormatDateTime(Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate 要格式环的时间
     * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * @throws ParseException
     * @see #getFormatDateTime(String)
     */
    public static Date getFormatDateTimeToTime(Date currDate) {
        return getFormatDateTime(getFormatDateTime(currDate));
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate 要格式化的时间
     * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * @throws ParseException
     * @see #getFormatDateTime(String, String)
     */
    public static Date getFormatDateTime(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @param currDate 要格式化的时间
     * @return String 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     * @see #getFormatDateTime(Date, String)
     */
    public static String getFormatDateTime_CN(Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @param currDate 要格式化的时间
     * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     * @throws ParseException
     * @see #getFormatDateTime_CN(String)
     */
    public static Date getFormatDateTimeToTime_CN(Date currDate) {
        return getFormatDateTime_CN(getFormatDateTime_CN(currDate));
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @param currDate 要格式化的时间
     * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     * @throws ParseException
     * @see #getFormatDateTime(String, String)
     */
    public static Date getFormatDateTime_CN(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate 要格式化的时间
     * @param format   时间格式，如yyyy-MM-dd HH:mm:ss
     * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
     * @see SimpleDateFormat#format(Date)
     */
    public static String getFormatDateTime(Date currDate,
                                           String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(TIME_FORMAT);

            return dtFormatdB.format(currDate);
        }
    }

    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate 要格式化的日期
     * @param format   日期格式，如yyyy-MM-dd
     * @return Date 返回格式化后的日期，格式由参数<code>format</code>定义，如yyyy-MM-dd，如2006-02-15
     * @throws ParseException
     * @see SimpleDateFormat#parse(String)
     */
    public static Date getFormatDate(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate 要格式化的时间
     * @param format   时间格式，如yyyy-MM-dd HH:mm:ss
     * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
     * @throws ParseException
     * @see SimpleDateFormat#parse(String)
     */
    public static Date getFormatDateTime(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到本日的上月时间 如果当日为2007-9-1,那么获得2007-8-1
     */
    public static String getDateBeforeMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     *
     *
     */
    public static String getDateBeforeDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到格式化后的当前系统日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @return String 返回格式化后的当前服务器系统日期，格式为yyyy-MM-dd，如2006-02-15
     * @see #getFormatDate(Date)
     */
    public static String getCurrDateStr() {
        return getFormatDate(getCurrDate());
    }

    /**
     * 得到格式化后的当前系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @return String 返回格式化后的当前服务器系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15
     * 15:23:45
     * @see #getFormatDateTime(Date)
     */
    public static String getCurrDateTimeStr() {
        return getFormatDateTime(getCurrDate());
    }

    /**
     * 得到格式化后的当前系统日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @return String 返回当前服务器系统日期，格式为yyyy年MM月dd日，如2006年02月15日
     * @see #getFormatDate(Date, String)
     */
    public static String getCurrDateStr_CN() {
        return getFormatDate(getCurrDate(), DATE_FORMAT_CN);
    }

    /**
     * 得到格式化后的当前系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @return String 返回格式化后的当前服务器系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日
     * 15:23:45
     * @see #getFormatDateTime(Date, String)
     */
    public static String getCurrDateTimeStr_CN() {
        return getFormatDateTime(getCurrDate(), TIME_FORMAT_CN);
    }

    /**
     * 得到系统当前日期的前或者后几天
     *
     * @param iDate 如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @return Date 返回系统当前日期的前或者后几天
     * @see Calendar#add(int, int)
     */
    public static Date getDateBeforeOrAfter(int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后几天
     *
     * @param iDate 如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几天
     * @see Calendar#add(int, int)
     */
    public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 得到格式化后的月份，格式为yyyy-MM，如2006-02
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的月份，格式为yyyy-MM，如2006-02
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatMonth(Date currDate) {
        return getFormatDate(currDate, MONTH_FORMAT);
    }

    /**
     * 得到格式化后的日，格式为yyyyMMdd，如20060210
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的日，格式为yyyyMMdd，如20060210
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatDay(Date currDate) {
        return getFormatDate(currDate, DAY_FORMAT);
    }

    /**
     * 得到当月第一天，格式为yyyy-MM-dd，如2006-02-01
     *
     * @return String 返回当月第一天，
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static Date getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
    }

    /**
     * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     *
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatFirstDayOfMonth() {
        return getFormatDate(getFirstDayOfMonth(), DATE_FORMAT);
    }

    /**
     * 下月第一天，格式为yyyy-MM-dd，如2006-02-01
     *
     * @return String 下月第一天，
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static Date getFirstDayOfNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, +1);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
    }

    /**
     * 得到格式化后的下月第一天，格式为yyyy-MM-dd，如2006-02-01
     *
     * @return String 返回格式化后的下月第一天，格式为yyyy-MM-dd，如2006-02-01
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatFirstDayOfNextMonth() {
        return getFormatDate(getFirstDayOfNextMonth(), DATE_FORMAT);
    }

    /**
     * 得到指定日期格式化后当月第一天，格式为yyyy-MM-dd，如2006-02-01
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatFirstDayOfMonth(Date currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到指定日期格式化后当月最后一天，格式为yyyy-MM-dd，如2006-02-01
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static String getFormatLastDayOfMonth(Date currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        //int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        int firstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到指定日期当月第一天
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static Date getFirstDayOfMonth(Date currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
    }

    /**
     * 得到指定日期当月最后一天
     *
     * @param currDate 要格式化的日期
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     */
    public static Date getLastDayOfMonth(Date currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后几小时
     *
     * @param iHour 如果要获得前几小时日期，该参数为负数； 如果要获得后几小时日期，该参数为正数
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几小时
     * @see Calendar#add(int, int)
     */
    public static Date getDateBeforeOrAfterHours(Date curDate, int iHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.HOUR_OF_DAY, iHour);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后几小时
     *
     * @param iHour 如果要获得前几小时日期，该参数为负数； 如果要获得后几小时日期，该参数为正数
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几小时
     * @see Calendar#add(int, int)
     */
    public static String getDateBeforeOrAfterHours(String curDate, int iHour) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        String curDateStr = "";
        try {
            Date date = sdf.parse(curDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR_OF_DAY, iHour);
            curDateStr = sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDateStr;
    }

    /**
     * 得到日期的前或者后几小时
     *
     * @param minute 如果要获得前几小时日期，该参数为负数； 如果要获得后几小时日期，该参数为正数
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几小时
     * @see Calendar#add(int, int)
     */
    public static String getDateBeforeOrAfterMinute(String curDate, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        String curDateStr = "";
        try {
            Date date = sdf.parse(curDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, minute);
            curDateStr = sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDateStr;
    }

    /**
     * 判断日期是否在当前周内
     *
     * @param curDate
     * @param compareDate
     * @return
     * @throws ParseException
     */
    public static boolean isSameWeek(Date curDate, Date compareDate) {
        if (curDate == null || compareDate == null) {
            return false;
        }

        Calendar calSun = Calendar.getInstance();
        calSun.setTime(getFormatDateToDate(curDate));
        calSun.set(Calendar.DAY_OF_WEEK, 1);

        Calendar calNext = Calendar.getInstance();
        calNext.setTime(calSun.getTime());
        calNext.add(Calendar.DATE, 7);

        Calendar calComp = Calendar.getInstance();
        calComp.setTime(compareDate);
        if (calComp.after(calSun) && calComp.before(calNext)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 时间查询时,结束时间的 23:59:59
     */
    public static String addDateEndfix(String datestring) {
        if ((datestring == null) || datestring.equals("")) {
            return null;
        }
        return datestring + " 23:59:59";
    }

    /**
     * 返回格式化的日期
     *
     * @param datePre 格式"yyyy-MM-dd HH:mm:ss";
     * @return
     * @throws ParseException
     */
    public static Date formatEndTime(String datePre) {
        if (datePre == null)
            return null;
        String dateStr = addDateEndfix(datePre);
        return getFormatDateTime(dateStr);
    }

    // date1加上compday天数以后的日期与当前时间比较，如果大于当前时间返回true，否则false
    public static Boolean compareDay(Date date1, int compday) {
        if (date1 == null)
            return false;
        Date dateComp = getDateBeforeOrAfter(date1, compday);
        Date nowdate = new Date();
        if (dateComp.after(nowdate))
            return true;
        else
            return false;
    }

    /**
     * 进行时段格式转换，对于输入的48位的01串，将进行如下操作： <li>1.先将输入中每个0变成两个0，每个1变成2个1，形成一个96位的二进制串。
     * </li> <li>2.将上述的96位的二进制串分成3组，每组32位。</li> <li>3.将每个32位的二进制串转换成一个8位的16进制串。</li>
     * <li>4.将3个8位的16进制串合并成一个串，中间以","分割。</li>
     *
     * @param timespan 一个48位的二进制串，如：
     *                 "011111111011111111111111111111111111111111111110"
     * @return 一个16进制串，每位间以","分割。如："3fffcfff,ffffffff,fffffffc"
     */
    public static String convertBinaryTime2Hex(String timespan) {
        if (timespan == null || timespan.equals("")) {
            return "";
        }

        String ret = "";
        String tmp = "";
        for (int i = 0; i < timespan.length(); i++) {
            tmp += timespan.charAt(i);
            tmp += timespan.charAt(i);
            // tmp += i;
            if ((i + 1) % 16 == 0) {
                if (!ret.equals("")) {
                    ret += ",";
                }
                Long t = Long.parseLong(tmp, 2);
                String hexStr = Long.toHexString(t);
                if (hexStr.length() < 8) {
                    int length = hexStr.length();
                    for (int n = 0; n < 8 - length; n++) {
                        hexStr = "0" + hexStr;
                    }
                }

                ret += hexStr;
                tmp = "";
            }
        }

        return ret;
    }

    /**
     * 进行时段格式转换，将输入的26位的2进制串转换成48位的二进制串。
     *
     * @param timespan 一个16进制串，每位间以","分割。如："3fffcfff,ffffffff,fffffffc"
     * @return 一个48位的二进制串，如："011111111011111111111111111111111111111111111110"
     */
    public static String convertHexTime2Binary(String timespan) {
        if (timespan == null || timespan.equals("")) {
            return "";
        }

        String tmp = "";
        String ret = "";
        String[] strArr = timespan.split(",");
        for (int i = 0; i < strArr.length; i++) {
            String binStr = Long.toBinaryString(Long.parseLong(strArr[i], 16));
            if (binStr.length() < 32) {
                int length = binStr.length();
                for (int n = 0; n < 32 - length; n++) {
                    binStr = "0" + binStr;
                }
            }
            tmp += binStr;
        }

        for (int i = 0; i < 48; i++) {
            ret += tmp.charAt(i * 2);
        }

        return ret;
    }

    /**
     * 进行时段格式转换，将输入的32位的10进制串转换成48位的二进制串。
     *
     * @param timespan 一个16进制串，每位间以","分割。如："1234567890,1234567890,1234567890c"
     * @return 一个48位的二进制串，如："011111111011111111111111111111111111111111111110"
     */
    public static String convertDecTime2Binary(String timespan) {
        if (timespan == null || timespan.equals("")) {
            return "";
        }

        String tmp = "";
        String ret = "";
        String[] strArr = timespan.split(",");
        for (int i = 0; i < strArr.length; i++) {
            String binStr = Long.toBinaryString(Long.parseLong(strArr[i], 10));
            if (binStr.length() < 32) {
                int length = binStr.length();
                for (int n = 0; n < 32 - length; n++) {
                    binStr = "0" + binStr;
                }
            }
            tmp += binStr;
        }

        for (int i = 0; i < 48; i++) {
            ret += tmp.charAt(i * 2);
        }

        return ret;
    }

    /**
     * 进行时段格式转换，对于输入的48位的01串，将进行如下操作： <li>1.先将输入中每个0变成两个0，每个1变成2个1，形成一个96位的二进制串。
     * </li> <li>2.将上述的96位的二进制串分成3组，每组32位。</li> <li>3.将每个32位的二进制串转换成一个10位的10进制串。
     * </li> <li>4.将3个8位的16进制串合并成一个串，中间以","分割。</li>
     *
     * @param timespan 一个48位的二进制串，如：
     *                 "011111111011111111111111111111111111111111111110"
     * @return 一个16进制串，每位间以","分割。如："1234567890,1234567890,1234567890"
     */
    public static String convertBinaryTime2Dec(String timespan) {
        if (timespan == null || timespan.equals("")) {
            return "";
        }

        String ret = "";
        String tmp = "";
        for (int i = 0; i < timespan.length(); i++) {
            tmp += timespan.charAt(i);
            tmp += timespan.charAt(i);
            // tmp += i;
            if ((i + 1) % 16 == 0) {
                if (!ret.equals("")) {
                    ret += ",";
                }
                Long t = Long.parseLong(tmp, 2);
                String decStr = Long.toString(t);
                if (decStr.length() < 10) {
                    int length = decStr.length();
                    for (int n = 0; n < 10 - length; n++) {
                        decStr = "0" + decStr;
                    }
                }

                ret += decStr;
                tmp = "";
            }
        }

        return ret;
    }

    /**
     * 第二个参数减去第一个参数，差多少天
     *
     * @return two与one的差值（天） author:金龙
     */
    public static int compareDay(Date one, Date two) {
        long num1 = one.getTime();
        long num2 = two.getTime();
        return (int) ((num2 - num1) / (24 * 3600 * 1000));
    }

    /**
     * @param one 日期格式，如yyyy-MM-dd
     * @param two 日期格式，如yyyy-MM-dd
     * @return
     * @throws ParseException
     * @Description 第二个参数减去第一个参数，差多少天
     */
    public static int compareDay(String one, String two) {
        Date oneDate = getFormatDate(one);
        Date twoDate = getFormatDate(two);
        return compareDay(oneDate, twoDate);
    }

    /**
     * @param one 日期格式，如yyyy-MM-dd
     * @param two 日期格式，如yyyy-MM-dd
     * @return
     * @Description 第二个参数减去第一个参数，第2个日期是否小于第一个日期
     */
    public static boolean compareDate_lt(String one, String two) {
        if (compareDay(one, two) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 第二个参数减去第一个参数，差多少小时
     *
     * @return two与one的差值（小时） author:金龙
     */
    public static int compareHour(Date one, Date two) {
        long num1 = one.getTime();
        long num2 = two.getTime();
        return (int) ((num2 - num1) / (3600 * 1000));
    }

    /**
     * 第二个参数减去第一个参数，差多少小时
     *
     * @return two与one的差值（小时） author:金龙
     */
    public static int compareHour(String one, String two) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        long num1 = 0;
        long num2 = 0;
        try {
            num1 = sdf.parse(one).getTime();
            num2 = sdf.parse(two).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) ((num2 - num1) / (3600 * 1000));
    }

    /**
     * 第二个参数减去第一个参数，差多少分钟
     *
     * @return two与one的差值（分钟） author:金龙
     */
    public static int compareMinuter(Date one, Date two) {
        long num1 = one.getTime();
        long num2 = two.getTime();
        return (int) ((num2 - num1) / (60 * 1000));
    }

    /**
     * 第二个参数减去第一个参数，差多少分钟
     *
     * @return two与one的差值（分钟） author:金龙
     */
    public static int compareMinuter(String one, String two) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        long num1 = 0;
        long num2 = 0;
        try {
            num1 = sdf.parse(one).getTime();
            num2 = sdf.parse(two).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) ((num2 - num1) / (60 * 1000));
    }

    /**
     * 第二个参数减去第一个参数，差多少秒
     *
     * @param one
     * @param two
     * @return
     * @Description
     */
    public static int compareSeconds(Date one, Date two) {
        long num1 = one.getTime();
        long num2 = two.getTime();
        return (int) ((num2 - num1) / (1000));
    }

    /**
     * 第二个参数减去第一个参数，差多少秒
     *
     * @param one
     * @param two
     * @return
     * @Description
     */
    public static int compareSeconds(String one, String two) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        long num1 = 0;
        long num2 = 0;
        try {
            num1 = sdf.parse(one).getTime();
            num2 = sdf.parse(two).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) ((num2 - num1) / (1000));
    }

    /**
     * @param sDate
     * @return
     * @throws ParseException
     * @Description 格式化为日期，传入格式为yyyyMMddHHmmss
     */
    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(LONG_FORMAT);
        Date d = null;

        try {
            if ((sDate != null) && (sDate.length() == LONG_FORMAT.length())) {
                d = dateFormat.parse(sDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }

    /**
     * @param date
     * @return
     * @Description 日期格式化为yyyyMMddHHmmss
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(LONG_FORMAT);

        return getDateString(date, dateFormat);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != LONG_FORMAT.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(LONG_FORMAT);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * 获得指定日期的前一天
     *
     * @return 昨天
     */
    public static String getSpecifiedDayBefore() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 获得指定日期的前一天
     *
     * @return 昨天
     */
    public static String getSpecifiedDayBeforeOrAfter(String dateStr,Integer iDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, iDate);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 方法功能描述:获得上月
     * @Auther: gjq
     * @Date 2017/7/28 16:07
     * @param
     * @return
     */
    public static String getLastMonth(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        try {
            date=dateFormat.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date); //设置为当前时间
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
            date = calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  dateFormat.format(date);
    }



}
