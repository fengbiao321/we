package junit;

import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;

/**
 * @Auther: biao.feng
 * @Date: 2018/8/26 17:21
 * @Description:
 */
public class MyTest {

    public void method1() {
        System.out.println("aaaaaaa");
        ((MyTest)AopContext.currentProxy()).method2();
        System.out.println(AopUtils.isAopProxy(this));
//        this.method2();

    }

    public void method2 () {
        System.out.println("bbbbbbbbb");
    }
}
