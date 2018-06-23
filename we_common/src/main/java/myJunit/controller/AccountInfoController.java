package myJunit.controller;

import myJunit.annotation.ApiRequest;
import myJunit.param.account.QueryAccountInfoParam;
import myJunit.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/23 13:27
 * @Description:
 */
@Controller
@RequestMapping("/account")
public class AccountInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiRequest
    @RequestMapping("/queryBaseInfo")
    @ResponseBody
    public Object queryAccountBaseInfo(QueryAccountInfoParam param) {
        return  userInfoService.userTotalCount();
    }

}
