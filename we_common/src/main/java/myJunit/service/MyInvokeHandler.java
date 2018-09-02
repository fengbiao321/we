package myJunit.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author biao.feng
 * @Date 2018/8/31
 * @Description
 */
public class MyInvokeHandler implements InvocationHandler{

    // 目标对象
    private Object target;

    public MyInvokeHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(target, args);//这里传入的是原对象
        System.out.println("after");
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }

}
