package myJunit.util;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    @Test
    public void testStream () throws IOException {

        byte [] buffer = new byte[10];
        String string = "12345678910";

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string.getBytes());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream, buffer.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream, buffer.length);

        int len ;
        while ((len = bufferedInputStream.read(buffer)) != -1){
            System.out.println(len);
            bufferedOutputStream.write(buffer, 0, len);
        }
        System.out.println(len);
        String out = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        System.out.println(out);

    }

    @Test
    public void testJson (){

        Map<String, Object> map = Maps.newHashMap();
        map.put("fsfs",1);
        map.put("fsfsfsfsfsf",2);
        String string = JsonUtils.parse2BeautifulJsonString(map);
        System.out.println(string);

        System.out.println(JsonUtils.parse2JsonString(map));

    }


}
