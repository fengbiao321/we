package myJunit.exception;

import myJunit.constant.RspCodeMsg;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 11:32
 * @Description: 响应运行时异常
 */
public class RspRuntimeException extends RuntimeException{

    private RspCodeMsg rspCodeMsg;

    public RspRuntimeException(RspCodeMsg rspCodeMsg) {
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspRuntimeException(String message, RspCodeMsg rspCodeMsg) {
        super(message);
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspRuntimeException(String message, Throwable cause, RspCodeMsg rspCodeMsg) {
        super(message, cause);
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspRuntimeException(Throwable cause, RspCodeMsg rspCodeMsg) {
        super(cause);
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, RspCodeMsg rspCodeMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspCodeMsg getRspCodeMsg() {
        return rspCodeMsg;
    }

    public void setRspCodeMsg(RspCodeMsg rspCodeMsg) {
        this.rspCodeMsg = rspCodeMsg;
    }
}
