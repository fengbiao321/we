package myJunit.systemInstall;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/9 17:46
 * @Description: 系统配置参数
 */
@Component
public class SysConfig implements InitializingBean {

    public static String testRootURL = "";

    /**
     * 初始化加载系统配置参数
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Value("${testURL}")
    public  void setTestRootURL(String testRootURL) {
        SysConfig.testRootURL = testRootURL;
    }
}
