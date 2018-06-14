package myJunit.util;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Auther: Administrator
 * @Date: 2018/6/9 17:56
 * @Description: 用于一些上下文件的工具类
 */
public abstract class ContextUtil {

    private ContextUtil(){

    }

    /**
     *  判断源字符串是否为空，如果是空的话就以后边的字符串代替
     * @param source
     * @param replace
     * @return
     */
    public static String nvl(String source, String replace){

        if (source == null || "".equals(source.trim())) {
            return replace;
        }
        return source.trim();
    }

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

}
