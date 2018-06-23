package myJunit.constant;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 10:45
 * @Description: 异常返回信息枚举类，每个模块
 */
public enum RspCodeMsg {

    //1 开头 通用异常信息
    SYSTEM_DEAL_SUCCESS("10000", "系统处理成功"),
    CHECK_PARAM_FAIL("10001","参数检查失败"),
    SYSTEM_UNKNOWN("10002", "系统未知异常");

    public String code;
    public String desc;

    RspCodeMsg(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "RspCodeMsg{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static ImmutableMap<String, RspCodeMsg> immutableMap = Maps.uniqueIndex(Lists.newArrayList(Arrays.asList(RspCodeMsg.values())), RspCodeMsg::getCode);

    /**
     * 根据code查询Rsp对应的实例
     * @param code
     * @return
     */
    public static RspCodeMsg getRspMsgByCode(String code) {

        return immutableMap.get(code);
    }

}
