package myJunit.util;

import com.google.common.collect.Lists;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/14 22:03
 * @Description: json工具类
 */
public class JsonUtils {

    private JsonUtils(){

    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 静态块设置json序列化相关参数
     */
    static {
        // 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    /**
     * 将对象转为json字符串
     * @return
     */
    public static String parse2JsonString (Object obj){

        if (null == obj) {
            return null;
        }

        String jsonString = null;
        try {
            jsonString = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * 将对象转为json字符串，并格式化字符串
     * @param obj
     * @return
     */
    public static String parse2BeautifulJsonString (Object obj){
        if (null == obj) {
            return null;
        }

        String jsonString = null;
        try {
            jsonString = obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
