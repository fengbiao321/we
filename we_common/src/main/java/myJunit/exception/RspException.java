package myJunit.exception;

import myJunit.constant.RspCodeMsg;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 10:43
 * @Description: 响应异常
 */
public class RspException extends Exception{

    private RspCodeMsg rspCodeMsg;

    public RspException(RspCodeMsg rspCodeMsg) {
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspException(String message, RspCodeMsg rspCodeMsg) {
        super(message);
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspException(String message, Throwable cause, RspCodeMsg rspCodeMsg) {
        super(message, cause);
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspException(Throwable cause, RspCodeMsg rspCodeMsg) {
        super(cause);
        this.rspCodeMsg = rspCodeMsg;
    }

    public RspException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, RspCodeMsg rspCodeMsg) {
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
