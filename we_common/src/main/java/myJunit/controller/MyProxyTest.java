package myJunit.controller;

import myJunit.proxy.MyCglibProxy;
import myJunit.service.MyInvokeHandler;
import myJunit.service.Person;
import myJunit.service.Student;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @Author biao.feng
 * @Date 2018/8/31
 * @Description
 */
public class MyProxyTest {

    public static void main(String[] args) {
        //代理类输出到本地磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //--该设置用于输出cglib动态代理产生的类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "c:\\myProxyClass");

//        Student student = new Student();

        /*MyInvokeHandler myInvokeHandler = new MyInvokeHandler(student);

        Person person = (Person) myInvokeHandler.getProxy();*/
        MyCglibProxy myCglibProxy = new MyCglibProxy();
        Student student = (Student) myCglibProxy.getProxy();

        student.say("student-------");

    }

}
