package myJunit.service;

/**
 * @Author biao.feng
 * @Date 2018/8/31
 * @Description
 */
public class Student implements Person{

    @Override
    public void say(String context) {
        System.err.println(context);
    }
}
