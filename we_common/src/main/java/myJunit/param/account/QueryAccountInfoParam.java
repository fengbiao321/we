package myJunit.param.account;

import com.google.common.base.Preconditions;
import myJunit.param.AbstractParam;
import org.apache.commons.lang.StringUtils;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 13:31
 * @Description: 账户信息查询入参
 */
public class QueryAccountInfoParam extends AbstractParam {

    private String accountNo;

    private String accountName;

    @Override
    public void checkParam() {

        Preconditions.checkArgument(StringUtils.isNotBlank(accountNo), "账户号不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(accountName), "账户名不能为空");

    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
