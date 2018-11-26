package com.nowcoder.Interceptor;
import com.nowcoder.dao.LoginTicketDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求连接中加入user
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    UserDao userdao;
    @Autowired
    LoginTicketDao loginTicketDao;
    @Autowired
    HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket=null;
        if( httpServletRequest.getCookies()!=null){
            for(Cookie cookie:httpServletRequest.getCookies()){
                if("ticket".equals(cookie.getName())){
                    ticket=cookie.getValue();
                    break;
                }
            }
        }
        if(ticket!=null){
            LoginTicket loginTicket=loginTicketDao.selectByTicket(ticket);
            User user=userdao.selectById(loginTicket.getUserId());
            //
            hostHolder.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        User user=hostHolder.getUser();
        modelAndView.addObject("user",user);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
