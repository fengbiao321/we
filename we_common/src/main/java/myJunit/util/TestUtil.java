package myJunit.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/6/10 14:24
 * @Description:
 */
public class TestUtil {

    @Test
    public void test1(){
        Map<String,String> map = new HashMap<>();
        map.put("name","");
        String s = "";
        StringBuilder stringBuilder = new StringBuilder("");
        map.forEach((k,v) ->{
            stringBuilder.append(k).append("=").append(v).append("&");
        });
        s = stringBuilder.substring(0,stringBuilder.length()-1);
        System.out.println(s);
        System.out.println(stringBuilder.toString());

    }

}
