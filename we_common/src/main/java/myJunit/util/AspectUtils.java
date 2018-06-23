package myJunit.util;

import myJunit.constant.RspCodeMsg;
import myJunit.exception.RspException;
import myJunit.exception.RspRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 13:43
 * @Description: 切面工具工具类
 */
public class AspectUtils {

    /**
     * 获取监控的方法名称
     *
     * @param joinPoint
     * @param tag       项目标识
     * @return
     */
    public static String getMonitorKey(ProceedingJoinPoint joinPoint, String tag) {

        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            //tag + 类全名 + 方法名
            return ContextUtils.nvl(tag, "") + methodSignature.getDeclaringTypeName() + methodSignature.getName();
        }
        return "None_Interface_MonitorKey";
    }

    /**
     * 获取本地响应的内容
     *
     * @param e
     * @return
     */
    public static RspCodeMsg getExceptionRsp(Exception e) {
        RspCodeMsg rspCodeMsg = null;

        if (e instanceof RspException) {
            rspCodeMsg = ((RspException) e).getRspCodeMsg();
        }else if (e instanceof RspRuntimeException) {
            rspCodeMsg = ((RspRuntimeException) e).getRspCodeMsg();
        }
        //返回未知异常
        if (rspCodeMsg == null) {
            rspCodeMsg = RspCodeMsg.SYSTEM_UNKNOWN;
        }

        return rspCodeMsg;
    }

}
