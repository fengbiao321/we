package UserInofTest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import myJunit.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml"})
public class InofTest {

    @Autowired
    UserInfoService userInfoService;
    @Resource
    SqlSessionTemplate sqlSessionTemplateBatch;

    @Test
    public void test(){
        int count = userInfoService.userTotalCount();
        System.out.println(count);
    }

    @Test
//    @Transactional(rollbackFor = Exception.class)
//    @Rollback(false)
    public void testBatchUpdate(){
        List<Map<String,Object>> mapList = Lists.newArrayList();
        Map<String,Object> zhansan = Maps.newHashMap();
        zhansan.put("name","张三");
        zhansan.put("age","415");
        mapList.add(zhansan);
        Map<String,Object> lisi = Maps.newHashMap();
        lisi.put("name","李四");
        lisi.put("age","21");
        mapList.add(lisi);
        int count = userInfoService.batchUpdateUserInfo(mapList);
        System.out.println(count);
    }

    @Test
    public void testBatchUpdate1(){
        List<Map<String,Object>> mapList = Lists.newArrayList();
        Map<String,Object> zhansan = Maps.newHashMap();
        zhansan.put("name","张三");
        zhansan.put("age","415");
        mapList.add(zhansan);
        Map<String,Object> lisi = Maps.newHashMap();
        lisi.put("name","李四");
        lisi.put("age","21");
        mapList.add(lisi);

        int count =  sqlSessionTemplateBatch.update("myJunit.dao.UserInfoMapper.batchUpdateUserInfo",lisi);

        System.out.println(count);
    }


    public static void main(String[] args) {
//        BigDecimal bigDecimal = BigDecimal.ZERO;
//        add(bigDecimal);
//        System.out.println(bigDecimal);
//    String string = "\\[?!((去呼呼)|(客栈同))\\]";
      /*  String string = ".*\\[(?!.*(去呼呼|客栈同)).*\\]";
       Pattern p = Pattern.compile(string);
        Matcher matcher = p.matcher("肤色健康肤色块话费[国家法定机构开立多个]");
        System.out.println(matcher.find());
        System.out.println(matcher.group());*/



//        System.out.println("肤色健康肤色块话费[国家法定机构开立多个]".matches(string));


        String reg = "\\[(?!.*(不和谐)).*\\]";
        System.out.println("卡看到底是不[是和谐根据国家馆]".matches(reg));
        System.out.println("卡看到底是不是和谐发送方式]".matches(reg));


        System.out.println("fskhfsk]".matches(".*\\]"));

    }

    public static void add (BigDecimal bigDecimal){
        bigDecimal.add(BigDecimal.ONE);

    }


}
