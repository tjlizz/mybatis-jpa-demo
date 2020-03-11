package lz.cim.api.core.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Common {

    public static String GetKey() {
        return UUID.randomUUID().toString();
    }

       public static Date GetDate(String pattern) {
        if (pattern.isEmpty()) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式

        java.util.Date time = null;
        try {
            time = df.parse(df.format(new Date()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return time;
    }   public static Date GetDateTime() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        java.util.Date time = null;
        try {
            time = df.parse(df.format(new Date()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return time;
    }


    public static String GetStringDate(long date) {
        Date dates = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String GetStringDate(Date date) {
        if (date != null) {
            Date dates = date;
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
            return sdf.format(date);
        } else {
            return "";
        }
    }

    public static String getStringDate() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);

    }


    /*
     *新建指定日期
     * */
    public static Date GetDateByStringTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(date);
            return d;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return new Date();
        }
    }

    /*
     *新建指定日期时分秒
     * */
    public static Date GetDateByStringTimems(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf.parse(date);
            return d;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return new Date();
        }
    }


    public static Boolean IsEmptyOrNull(String str) {

        if (str == null || str.length() <= 0 || str.isEmpty())
            return true;
        return false;
    }

    //返回当前年份
    public static int getYear() {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回当前月份
    public static int getMonth() {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }

    public static int getDay() {
        Date date = new Date();
        String day = new SimpleDateFormat("dd").format(date);
        return Integer.parseInt(day);
    }

    //判断闰年
    public static boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    /**
     * 取得当月天数
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /*
     * 获取日期百分比
     * */
    /*
     * 获取指定日期百分比
     * */
    public static Float getPercentage(Date date) {
        Integer day = getDay(date);
        Integer month = getMonth(date);
        Integer year = getYear(date);
        Integer monthDays = getMonthLastDay(year, month);
        return (float) day / (float) monthDays;
    }

    //返回指定年份
    public static int getYear(Date date) {
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回指定月份
    public static int getMonth(Date date) {
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }

    //返回指定日期
    public static int getDay(Date date) {
        String day = new SimpleDateFormat("dd").format(date);
        return Integer.parseInt(day);
    }


    /*
     * 日期减去天数
     * */
    public static Date SubDate(Date date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -day);
        return calendar.getTime();
    }

    /*
     * 日期减去天数
     * */
    public static String SubDateToString(Date date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -day);
        return sdf.format(calendar.getTime());
    }

    public static String SubDateToString(Date date, int day, String pattern) {
        if (pattern.equals("")) pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -day);
        return sdf.format(calendar.getTime());
    }

    /*
     *日期加一天
     * */
    public static Date DateAddOne(Date dateTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.add(calendar.DATE, 1);
        return dateTime = calendar.getTime();
    }


    /**
     * 时间转字符串
     *
     * @param dateTime
     * @return
     */
    public static String dateTString(Date dateTime, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String str = format.format(dateTime); //2013-01-14
        return str;
    }

    /*
     * 给当前时间增加时间
     *
     *
     */
    public static String AddTime(int min) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, min);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(now.getTimeInMillis());
        return dateStr;
    }

    /**
     * 获得当月1号零时零分零秒
     *
     * @return
     */
    public static Date initDateByMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取传入当日0点
     */
    public static Date StartTime(Date time) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(time);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当日0点
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
     * 根据传入时间获得传入时间2号零时零分零秒
     * @return
     */
//    public static Date GetinitDateByMonth(Date Time){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(Time);
//        calendar.set(Calendar.DAY_OF_MONTH, 2);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        return calendar.getTime();
//    }

    /**
     * 获取传入日期23点
     */
    public static Date getEndTime(Date time) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(time);
        todayStart.set(Calendar.HOUR_OF_DAY, 23);
        todayStart.set(Calendar.MINUTE, 59);
        todayStart.set(Calendar.SECOND, 59);
        todayStart.set(Calendar.MILLISECOND, 999);
        return todayStart.getTime();
    }

    /**
     * 根据传入时间获得传入时间1号零时零分零秒
     *
     * @return
     */
    public static Date GetinitDateByMonthOne(Date Time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Time);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /*
     *新建指定日期
     * */
    public static String GetStringTimeToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String str = sdf2.format(sdf.parse(date));
            return str;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String GetSuffix(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;

    }


}