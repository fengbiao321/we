package junit;

import myJunit.bean.ImmutableBean;
import myJunit.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/26 22:34
 * @Description:
 */
@ContextConfiguration({"classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAsyMethod {

    @Test
    public void testAsy() {

        sayHello();
        System.out.println("jjjj");

    }

    @Async
    public void sayHello(){
        System.out.println("hello world!!!!");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hhhhh");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperties());
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void testImmutable() {
        String name = "fsfs";
        Integer age = 1;
        Date date = new Date();

        ImmutableBean immutableBean = new ImmutableBean(name, age, date);

        System.out.println(immutableBean);
        date.setTime(org.apache.commons.lang.time.DateUtils.addDays(date, 1).getTime());
        System.out.println(immutableBean);

    }

    @Test
    public void testException() {
        System.out.println(test());;
    }

    public int test() {
        try{
            int i = Integer.parseInt("s");
            return i;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("e", e);
        }finally {
            System.out.println("finally");
            return 3;
        }
    }

}
