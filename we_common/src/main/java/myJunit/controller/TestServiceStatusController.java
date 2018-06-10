package myJunit.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import myJunit.annotation.myTestAnnotation;
import myJunit.bean.User;
import myJunit.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Controller
@RequestMapping("/test")
public class TestServiceStatusController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/postmanTest")
    public ModelAndView postManTest(@RequestParam String account,@RequestParam String password,RedirectAttributes attr){
        System.out.println("zhanghao"+account+"密码"+password);
//        ModelAndView mav = new ModelAndView("redirect:/WEB-INF/test/success.jsp");
        ModelAndView mav = new ModelAndView("redirect:/test/redirect");
        attr.addFlashAttribute("account","1");
//        attr.addAttribute("account","扎三");
//        User user = new User();
//        user.setName("zhansa");
//        mav.addObject("user",user);

        return mav;
    }

    @RequestMapping("/RepsoneRedirect")
    public void testRepsoneRedirect(HttpServletResponse response,RedirectAttributes attr) throws IOException {
        attr.addFlashAttribute("account","1");
        response.sendRedirect("/test/redirect.do");

    }

    @RequestMapping("/show")
    public void postReturnVoid(Model model, @RequestParam("name") String myName){
//        Map<String,Object> map = Maps.newHashMap();
        model.addAttribute("name","zhangsn");
    }

    @RequestMapping("/redirect")
    @ResponseBody
    public String testRedirect(@ModelAttribute("account") String account){
        return account;
    }

    @ResponseBody
    @RequestMapping("/testException")
    public String testExceptionRollBack() throws Exception {
        List<Map<String,Object>> mapList = Lists.newArrayList();
        Map<String,Object> zhansan = Maps.newHashMap();
        zhansan.put("name","张三");
        zhansan.put("age","105");
        mapList.add(zhansan);
        Map<String,Object> lisi = Maps.newHashMap();
        lisi.put("name","李四");
        lisi.put("age","0");
        mapList.add(lisi);
        int i = userInfoService.batchUpdateUserInfoException(mapList);
        return i+"";
    }

}
