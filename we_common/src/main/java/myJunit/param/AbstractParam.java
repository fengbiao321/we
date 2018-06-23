package myJunit.param;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 10:34
 * @Description: 入参基类,所有入参都应该基于该类
 */
public abstract class AbstractParam {

    //被子类覆盖，用于参数效验
    public abstract void checkParam();


}
