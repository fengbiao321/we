package myJunit.constant;

import java.io.Serializable;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 14:22
 * @Description: 响应结果统一封装
 */
public class Response implements Serializable {

    public static final String ver = "1.0";

    private String code;

    private String  msg;

    private String version;

    public Response(RspCodeMsg codeMsg) {
        this(codeMsg.getCode(), codeMsg.getDesc());
    }

    public Response(RspCodeMsg codeMsg, String msg) {
        this(codeMsg.getCode(), msg ==null ? codeMsg.getDesc() : msg);
    }

    public Response(String code, String msg) {
       this(code, msg, ver);
    }

    public Response(String code, String msg, String version) {
        this.code = code;
        this.msg = msg;
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
