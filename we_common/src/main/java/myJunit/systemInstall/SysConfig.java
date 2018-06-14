package myJunit.systemInstall;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/9 17:46
 * @Description: 系统配置参数
 */
@Component
public class SysConfig {

    public static String testRootURL = "";

    @Value("${testURL}")
    public  void setTestRootURL(String testRootURL) {
        SysConfig.testRootURL = testRootURL;
    }
}
