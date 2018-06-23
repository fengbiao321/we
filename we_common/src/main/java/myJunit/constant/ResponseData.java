package myJunit.constant;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 14:39
 * @Description: 封装数据响应
 */
public class ResponseData extends Response {

    private Object data;

    public ResponseData(RspCodeMsg codeMsg, Object data) {
        this(codeMsg.getCode(), codeMsg.getDesc(), data);
    }

    public ResponseData(String code, String msg, Object data) {
        super(code, msg);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
