package myJunit.servlet;

import myJunit.systemInstall.SysConfig;
import myJunit.util.ContextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/9 17:56
 * @Description: 基础servlet转发
 */
public abstract class BaseForwardProxyServlet extends HttpServlet {

    public static final String GET_METHOD = "GET";

    public abstract String getRootUrl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 获取转发请求路径
         * 2. 设置参数http请求参数
         * 3. 处理返回数据
         */
        URL url = new URL(getExRequest(request));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置请求格式
        connection.setConnectTimeout(5 * 1000);         // 设置访问超时时间
        connection.setRequestMethod(GET_METHOD);       // 设置请求方式
        connection.setDoInput(false);                    // 设置是否从httpUrlConnection读入
        connection.setUseCaches(true);                  // 设置是否使用缓存
        connection.setInstanceFollowRedirects(true);    // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
        connection.setDoOutput(true);                   // 设置是否向HttpURLConnection输出，带参
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Connection", "Keep-Alive");              // 维持长连接
        connection.setRequestProperty("Charset", "UTF-8");

        this.dealRemoteStream(connection.getInputStream(), response.getOutputStream());

    }

    /**
     * 处理返回流数据
     * @param inputStream
     * @param outputStream
     */
    public void dealRemoteStream (InputStream inputStream, OutputStream outputStream) throws IOException {

        if (inputStream == null || outputStream == null) {
            return;
        }
        //循环读取输入流中的数据，使用缓冲流进行读取 默认长度是8M
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        try {
            int len = 0;
            //read方法返回值是读取的下一个数据字节
            while ((len = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(len);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO 这里记录日志
        }finally{
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (bufferedOutputStream != null){
                bufferedOutputStream.close();
            }
        }

    }

    /**
     * 获取转发请求
     *
     * @param request
     * @return
     */
    public String getExRequest(HttpServletRequest request) {

        /**
         * 1.获取请求相对servlet路径
         * 2.如果是请求方式是get请求，组织请求参数
         * 3.拼接目标请求地址
         */
        String rootPath = this.getRootUrl();
        String pathInfo = request.getPathInfo();

        if (Objects.equals(request.getMethod(), GET_METHOD)) {
            pathInfo = pathInfo.concat("?").concat(request.getQueryString());
        }
        return ContextUtil.nvl(rootPath, "") + ContextUtil.nvl(pathInfo, "");
    }

}
