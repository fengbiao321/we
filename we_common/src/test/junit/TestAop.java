package junit;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: biao.feng
 * @Date: 2018/8/26 17:10
 * @Description:
 */
public class TestAop implements MethodInterceptor {

    private Object target;//业务类对象，供代理方法中进行真正的业务方法调用

    public Object getInstance(Object target) {
        this.target = target;  //给业务对象赋值
        Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
        enhancer.setSuperclass(this.target.getClass());  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] params, MethodProxy proxy) throws Throwable {
        System.err.println("=======before======");
        Object res = proxy.invokeSuper(obj, params);
        System.err.println("=======end======");
        return res;
    }
}
