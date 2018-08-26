package myJunit.service;

import myJunit.annotation.myTestAnnotation;
import myJunit.constant.RspCodeMsg;
import myJunit.dao.UserInfoMapper;
import myJunit.exception.RspRuntimeException;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    public Integer userTotalCount(){
        throw new RspRuntimeException(RspCodeMsg.SYSTEM_UNKNOWN);
//        return userInfoMapper.countUser();
    }

    public int batchUpdateUserInfo(List<Map<String,Object>> userinfo){
        return userInfoMapper.batchUpdateUserInfo(userinfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public int batchUpdateUserInfoException(List<Map<String,Object>> userinfo) throws Exception {
        Integer i = userInfoMapper.batchUpdateUserInfo(userinfo);
//        try{
            if (i > 0){
                throw new Exception();
            }
//        }catch (Exception e){
//            throw new RuntimeException();
//        }
        return i;
    }

    /**
     * 中间方法，用于测试本类调用后者存在切面情况
     */
    @myTestAnnotation
    public  void test() {
        System.out.println("已进入到中间方法中-----");
//        ((UserInfoService)AopContext.currentProxy()).sendMessage();
        this.sendMessage();
        System.out.println(AopUtils.isAopProxy(this));
        System.out.println(AopUtils.isCglibProxy(this));
        System.out.println("中间方法以结束-------");
    }

    @myTestAnnotation
    public void sendMessage() {
        /**
         * 模拟注册异步发送邮件等操作
         */
        System.out.println("调用发送邮件服务");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("邮件发送成功");
    }

}
