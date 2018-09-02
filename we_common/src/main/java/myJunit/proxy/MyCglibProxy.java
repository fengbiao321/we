package myJunit.proxy;

import myJunit.service.Student;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author biao.feng
 * @Date 2018/9/2
 * @Description cglib代理测试
 */
public class MyCglibProxy implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("----------before-----------");
        Object result = methodProxy.invokeSuper(o, objects);
        System.err.println("----------after-----------");
        return result;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        enhancer.setCallback(this);
        return enhancer.create();
    }

}
