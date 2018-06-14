package myJunit.servlet;

import myJunit.systemInstall.SysConfig;

/**
 * @Author biao.feng
 * @Date 2018/6/14
 * @Description 测试请求转发servlet
 */
public class TestForwardServlet extends BaseForwardProxyServlet {

    @Override
    public String getRootUrl() {
        return SysConfig.testRootURL;
    }

}
