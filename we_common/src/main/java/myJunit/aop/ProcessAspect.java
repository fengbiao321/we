package myJunit.aop;

import myJunit.annotation.ApiRequest;
import myJunit.constant.Response;
import myJunit.constant.ResponseData;
import myJunit.constant.RspCodeMsg;
import myJunit.exception.RspException;
import myJunit.exception.RspRuntimeException;
import myJunit.param.AbstractParam;
import myJunit.util.AspectUtils;
import myJunit.util.ContextUtils;
import myJunit.util.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 09:30
 * @Description: 过程处理切面，对于使用APIRequest注解的过程进行监控处理
 */
@Aspect
@Order(0)
@Component
public class ProcessAspect {

    /**
     * 切面环绕增强
     *
     * @param joinPoint
     */
    @Around("@annotation(apiRequest)")
    public Object doReqAspect(ProceedingJoinPoint joinPoint, ApiRequest apiRequest) throws Throwable {
        /**
         * 1. 调用controller处理请求
         * 2. 将结果封装到Response中,包括异常响应
         */

        Response rsp = null;
        //业务处理结果
        Object result = this.processController(joinPoint, apiRequest);

        if (result == null) {//无返回结果正常
            rsp = new Response(RspCodeMsg.SYSTEM_DEAL_SUCCESS);
        }else if (result instanceof Response){
            rsp = (Response)result;
        }else if (result instanceof RspCodeMsg) {
            rsp = new Response((RspCodeMsg) result);
        }else{
            rsp = new ResponseData(RspCodeMsg.SYSTEM_DEAL_SUCCESS, result);
        }
        return rsp;
    }

    public Object processController(ProceedingJoinPoint joinPoint, ApiRequest apiRequest) throws Throwable {

        /**
         * 1. 请求参数的效验
         * 2. 调用controller处理业务逻辑
         * 3. 封装返回结果（如果没有异常就返回业务逻辑结果，如果异常就返回RspCodeMsg异常对象），处理异常以及资源关闭
         */
        long startTime = System.currentTimeMillis();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        Object result  = null;

        try {
            this.checkParams(args, apiRequest);
        } catch (Exception e) {
            RspCodeMsg rspCodeMsg = RspCodeMsg.CHECK_PARAM_FAIL;
            if (e instanceof RspException) {
                rspCodeMsg = ((RspException) e).getRspCodeMsg();
            }else if (e instanceof RspRuntimeException) {
                rspCodeMsg = ((RspRuntimeException) e).getRspCodeMsg();
            }
            return new Response(rspCodeMsg.getCode(), e.getMessage());
        }

        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            //异常统一处理,自定义异常在这里进行扩展，此时返回的result是RspCodeMsg
            e.printStackTrace();
            result = AspectUtils.getExceptionRsp(e);
        } finally {
            //关闭必要资源，如果是ThreadLocal显示调用remove方法，避免oom
            long endTime = System.currentTimeMillis();
            System.out.println("调用{}接口 " + AspectUtils.getMonitorKey(joinPoint, "we") +
                    " 耗时:" + (endTime - startTime) + " 返回结果:{}" + JsonUtils.parse2JsonString(result));
        }
        //证明过程处理过程通畅，结果正常返回
        return result;
    }

    /**
     * 请求参数效验,这里应该所有请求参数集成一个基类，第一调用他/她的重写的效验参数的方法
     *
     * @param args
     * @param apiRequest
     */
    public void checkParams(Object[] args, ApiRequest apiRequest) {

        if (ContextUtils.ArraysIsNullOrEmpty(args)) {
            return;
        }

        for (Object arg : args) {

            if (!(arg instanceof AbstractParam)) {
                continue;
            }

            ((AbstractParam) arg).checkParam();

        }

    }

}
