package junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
