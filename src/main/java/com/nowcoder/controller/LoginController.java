package com.nowcoder.controller;

import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private  static Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @RequestMapping(path = {"/reg/"},method = {RequestMethod.POST})
    public String register(Model model,
                           HttpServletResponse response,
                           @RequestParam("username") String userName,
                           @RequestParam("password") String password){
        try {
            Map<String,String> map=new HashMap<>();
            map=userService.register(userName,password);
            if(map.containsKey("ticket")) {
                Cookie cookie=new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                //重定向到首页
                return "redirect:/";

            }else{
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }
        }catch (Exception e){
            logger.info("注册异常"+e.getMessage());
            return "login";
        }

    }

    @RequestMapping(path = {"/reglogin"},method = {RequestMethod.GET})
    public String toLogin(Model model){
        return "login";
    }
    @RequestMapping(path = {"/login/"},method = {RequestMethod.POST})
    public String login(Model model,
                           HttpServletResponse response,
                           @RequestParam("username") String userName,
                           @RequestParam("password") String password){
        try {
            Map<String,String> map=null;
            map=userService.login(userName,password);
            if(map.containsKey("ticket")) {
                Cookie cookie=new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                //重定向到首页
                return "redirect:/";

            }else{
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }
        }catch (Exception e){
            logger.info("注册异常"+e.getMessage());
            return "login";
        }
    }
}
