package myJunit.util;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Auther: Administrator
 * @Date: 2018/6/9 17:56
 * @Description: 用于一些上下文件的工具类
 *
 * 工具类一般设置为私有化构造器,如果不希望别人个改变类的本意可使用final进行修饰，如果提供基础方放希望扩展的话可以构造成抽象类
 */
public abstract class ContextUtils {

    private ContextUtils() {

    }

    /**
     * 判断源字符串是否为空，如果是空的话就以后边的字符串代替
     *
     * @param source
     * @param replace
     * @return
     */
    public static String nvl(String source, String replace) {

        if (source == null || "".equals(source.trim())) {
            return replace;
        }
        return source.trim();
    }

    /**
     * 转换url中中文数据
     * @param value
     * @return
     */
    public static String getURLEncoderValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        try {
            value = URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 判断数组是不是null或者空数组
     * @param args
     * @return 为null或者是空数组返回true
     */
    public static Boolean ArraysIsNullOrEmpty(Object[] args) {

        return args == null || args.length == 0 ? Boolean.TRUE : Boolean.FALSE;
    }

}
