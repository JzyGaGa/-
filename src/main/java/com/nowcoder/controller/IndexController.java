package com.nowcoder.controller;


import com.nowcoder.dao.UserDao;
import com.nowcoder.model.User;
import com.nowcoder.pojo.People;
import com.nowcoder.service.WendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by nowcoder on 2016/7/10.
 */
@Controller
public class IndexController {

    @RequestMapping(path = "/vm",method = {RequestMethod.GET})
    public String template(){
        System.out.println("66");
            return "home";
    }

    @RequestMapping(path = "/vm/{code}/{code1}")
    @ResponseBody()
    public String test(@PathVariable("code") String code,
                       @PathVariable("code") int code1
                       ){
        System.err.print(code);
        System.err.print(code1);

        return "home";
    }
    @RequestMapping(path = "/test1/{code}")
    @ResponseBody()
    public String test1(@PathVariable("code") String code,
                       @RequestParam(value = "code1") int code1,
                       @RequestParam(value="code2",required = false,defaultValue = "nes") String code2
                         ){
        System.err.print(code);
        System.err.print(code1);
        if(code2==null){
            System.out.print(code2);
        }

        return "home";
    }
    @RequestMapping(path = "demo2/set")
    public String demo2(Model model){
        List<String> list=Arrays.asList(new String[]{"Blue","Red","Cool"});
        model.addAttribute("list",list);
        model.addAttribute("value","niahao");
        Map<String,String> map=new HashMap<>();
        map.put("你好","小老弟");
        map.put("你","小老哥");
        model.addAttribute("map",map);
        People pop=new People();
        pop.setBornth("12");
        pop.setName("老贾");
        model.addAttribute("pop",pop);
        return "demo2";
    }
}

//    Set<Map.Entry<String,String>> sets= map.entrySet();
//        for (Map.Entry<String,String> set:sets
//             ) {
//        System.out.println(set.getKey()+"..."+set.getValue());
//    }
//    Set<String> key=map.keySet();
//        for (String k :
//    key) {
//        System.out.println(k+"..."+map.get(k));
//    }


















//    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
//
//    @Autowired
//    WendaService wendaService;
//

//
//    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
//    @ResponseBody
//    public String profile(@PathVariable("userId") int userId,
//                          @PathVariable("groupId") String groupId,
//                          @RequestParam(value = "type", defaultValue = "1") int type,
//                          @RequestParam(value = "key", required = false) String key) {
//        return String.format("Profile Page of %s / %d, t:%d k: %s", groupId, userId, type, key);
//    }
//
//    @RequestMapping(path = {"/vm"}, method = {RequestMethod.GET})
//    public String template(Model model) {
//        model.addAttribute("value1", "vvvvv1");
//        List<String> colors = Arrays.asList(new String[]{"RED", "GREEN", "BLUE"});
//        model.addAttribute("colors", colors);
//
//        Map<String, String> map = new HashMap<>();
//        for (int i = 0; i < 4; ++i) {
//            map.put(String.valueOf(i), String.valueOf(i * i));
//        }
//        model.addAttribute("map", map);
//        model.addAttribute("user", new User("LEE"));
//        return "home";
//    }
//
//    @RequestMapping(path = {"/request"}, method = {RequestMethod.GET})
//    @ResponseBody
//    public String request(Model model, HttpServletResponse response,
//                           HttpServletRequest request,
//                           HttpSession httpSession,
//                          @CookieValue("JSESSIONID") String sessionId) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("COOKIEVALUE:" + sessionId);
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            sb.append(name + ":" + request.getHeader(name) + "<br>");
//        }
//        if (request.getCookies() != null) {
//            for (Cookie cookie : request.getCookies()) {
//                sb.append("Cookie:" + cookie.getName() + " value:" + cookie.getValue());
//            }
//        }
//        sb.append(request.getMethod() + "<br>");
//        sb.append(request.getQueryString() + "<br>");
//        sb.append(request.getPathInfo() + "<br>");
//        sb.append(request.getRequestURI() + "<br>");
//
//        response.addHeader("nowcoderId", "hello");
//        response.addCookie(new Cookie("username", "nowcoder"));
//
//        return sb.toString();
//    }
//
//    @RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
//    public RedirectView redirect(@PathVariable("code") int code,
//                                 HttpSession httpSession) {
//        httpSession.setAttribute("msg", "jump from redirect");
//        RedirectView red = new RedirectView("/", true);
//        if (code == 301) {
//            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//        }
//        return  red;
//    }
//
//    @RequestMapping(path = {"/admin"}, method = {RequestMethod.GET})
//    @ResponseBody
//    public String admin(@RequestParam("key") String key) {
//        if ("admin".equals(key)) {
//            return "hello admin";
//        }
//        throw  new IllegalArgumentException("参数不对");
//    }
//
//    @ExceptionHandler()
//    @ResponseBody
//    public String error(Exception e) {
//        return "error:" + e.getMessage();
//    }

