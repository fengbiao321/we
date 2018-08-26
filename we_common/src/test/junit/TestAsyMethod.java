package junit;

import myJunit.bean.ImmutableBean;
import myJunit.util.DateUtils;
import net.sf.cglib.proxy.Enhancer;
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

    @Test
    public void testAop() {
       /* MyTest target = new MyTest();
        TestAop testAop = new TestAop();
        MyTest proxy = (MyTest) testAop.getInstance(target);
        proxy.method1();*/

        Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
        enhancer.setSuperclass(MyTest.class);  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(new TestAop());
        MyTest proxy = (MyTest) enhancer.create();
        proxy.method1();
        System.out.println("proxy class name:" + proxy.getClass().getName());
    }

}
