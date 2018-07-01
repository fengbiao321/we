package myJunit.util;

import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 09:22
 * @Description: 日期相关工具类
 * simpleDateFormat线程不安全主要是因为在格式化时内部共享Calendar对象
 */
public final class DateUtils {
    //私有化构造器
    private DateUtils() {
    }

    public static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (date == null || format == null || "".equals(format)) {
            return null;
        }
        return FastDateFormat.getInstance(format).format(date);
    }

    /**
     * 格式化日期
     *
     * @param calendar
     * @param format
     * @return
     */
    public static String formatDate(Calendar calendar, String format) {
        if (calendar == null || format == null || "".equals(format)) {
            return null;
        }
        return FastDateFormat.getInstance(format).format(calendar.getTime());
    }

    /**
     * 将字符串转为Date
     *
     * @param source
     * @param format
     * @return
     * 使用threadLocal 进行参数传递，TODO 记得释放资源
     */
    public static Date parse2Date(String source, String format) throws ParseException {

        if (source == null || format == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = threadLocal.get();

        if (simpleDateFormat == null) {
            //重新赋值
            simpleDateFormat = new SimpleDateFormat(format);
            threadLocal.set(simpleDateFormat);
        }

        return simpleDateFormat.parse(source);
    }

    /**
     * 释放当前线程中的ThreadLocal
     */
    public static void removeDateThreadLocal(){
        threadLocal.remove();
    }
}
