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
public class BaseForwardProxyServlet extends HttpServlet {

    public static String GET_METHOD = "GET";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1. 获取转发请求路径
         * 2. 设置参数http请求参数,组装提交请求参数
         * 3. 处理返回数据
         */
        URL url = new URL(getExRequest(SysConfig.testRootURL, request));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5 * 1000); // 设置访问超时时间
        connection.setRequestMethod(GET_METHOD); // 设置请求方式
        connection.setDoInput(true); // 设置是否从httpUrlConnection读入
        connection.setUseCaches(true); // 设置是否使用缓存
        connection.setInstanceFollowRedirects(true); // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向

        ByteArrayOutputStream byteArrayInputStream = null;
        byte[] buffer = new byte[1024];
        InputStream in = null;
        OutputStream out = null;

        try {
            //获取输出流
            if (!Objects.equals("", request.getQueryString())) {
                //设置请求格式
                connection.setDoOutput(true); // 设置是否向HttpURLConnection输出，带参
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestProperty("Content-length", "" + request.getQueryString().getBytes().length);

                out = connection.getOutputStream();
                out.write(request.getQueryString().getBytes("UTF-8"));
                out.flush();
            }
            // 从输入流中读取内容放到内存中
            byteArrayInputStream = new ByteArrayOutputStream();
            in = connection.getInputStream();
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                byteArrayInputStream.write(buffer, 0, len);
            }
            //处理内存中的数据
            response.getWriter().println(new String(byteArrayInputStream.toByteArray(), "UTF-8"));

        } catch (IOException e) {

        } finally {
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }


    }

    /**
     * 获取转发请求路径
     *
     * @param rootPath
     * @param request
     * @return
     */
    public String getExRequest(String rootPath, HttpServletRequest request) {
        String pathInfo = request.getPathInfo();

        if (Objects.equals(request.getMethod(), GET_METHOD)) {
            pathInfo = pathInfo.concat("?").concat(request.getQueryString());

        }
        return ContextUtil.nvl(rootPath, "") + ContextUtil.nvl(pathInfo, "");
    }

}
