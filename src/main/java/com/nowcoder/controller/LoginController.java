package com.nowcoder.controller;

import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private  static Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    /**
     * 注册逻辑
     * @param model
     * @param response
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(path = {"/reg/"},method = {RequestMethod.POST})
    public String register(Model model,
                           HttpServletResponse response,
                           @RequestParam("username") String userName,
                           @RequestParam(value = "next",required = false) String next,
                           @RequestParam("password") String password){
        try {
            Map<String,String> map=userService.register(userName,password);
            if(map.containsKey("ticket")) {
                Cookie cookie=new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                if(next!=null)
                    return "redirect:/"+next;
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

    /**
     * 跳转登录界面
     * @param model
     * @return
     */
    @RequestMapping(path = {"/reglogin"},method = {RequestMethod.GET})
    public String toLogin(Model model,@RequestParam(value = "next",required = false) String next){
        //这个可以下次传给url参数
        model.addAttribute("next",next);
        return "login";
    }

    /**
     * 登陆逻辑
     * @param model
     * @param response
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(path = {"/login/"},method = {RequestMethod.POST})
    public String login(Model model,
                           HttpServletResponse response,
                           @RequestParam("username") String userName,
                           @RequestParam(value = "next",required = false) String next,
                           @RequestParam(value = "remeberme",defaultValue = "false") boolean remeberMe,
                           @RequestParam("password") String password){
        try {
            Map<String,String> map=null;
            map=userService.login(userName,password);
            if(map.containsKey("ticket")) {
                Cookie cookie=new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                if(next!=null)
                    return "redirect:/"+next;
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

    @RequestMapping(path = {"/logout"},method = {RequestMethod.GET,RequestMethod.POST})
    public String loginOut(@CookieValue("ticket") String ticket){
        userService.loginOut(ticket);
        return  "redirect:/";
    }

}
