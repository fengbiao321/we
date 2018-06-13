package myJunit.aop;

import org.springframework.stereotype.Component;

/**
 * @Auther: Administrator
 * @Date: 2018/6/10 22:08
 * @Description: 使用POJO定义日志切面
 */
@Component
public class BaseAspectTest {

    /**
     * 定义起前置方法
     */
    public void before (){
        System.out.println("日志开始记录");
    }

    public void after (){
        System.out.println("日志记录完成");
    }

}
