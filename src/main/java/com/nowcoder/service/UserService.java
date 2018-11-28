package com.nowcoder.service;
import com.nowcoder.Utils.WendaUtil;
import com.nowcoder.dao.LoginTicketDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.nowcoder.Utils.WendaUtil.MD5;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    LoginTicketDao loginTicketDao;
    /**
     * 注册功能
     * @param username
     * @param password
     * @return
     */
    public Map<String, String> register(String username,String password){
        Map <String,String> map=new HashMap<>();
        if(StringUtils.isEmpty(username)){
            map.put("msg","用户名不能为空");
            return  map;
        }

        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return  map;
        }
        //判断user是否存在
        User user=userDao.findUserByName(username);
        if(user!=null){
            map.put("msg","用户名已经存在");
            return  map;
        }

        user=new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setPassword(MD5(password+user.getSalt()));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        userDao.addUser(user);

        String ticket=addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return  map;
    }

    public Map<String, String> login(String username,String password){
        Map <String,String> map=new HashMap<>();
        if(StringUtils.isEmpty(username)){
            map.put("msg","用户名不能为空");
            return  map;
        }

        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return  map;
        }

        //判断user是否存在
        User user=userDao.findUserByName(username);
        if(user==null){
            map.put("msg","该用户不存在");
            return map;
        }
        String toTest=WendaUtil.MD5(password+user.getSalt());
        if(!user.getPassword().equals(toTest)){
            map.put("msg","密码错误");
            return map;
        }
        String ticket=addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return  map;
    }
    public void loginOut(String tiket){
        loginTicketDao.updateStatus(tiket,1);
    }
    /**
     * 第一次生成cookie
     * @param userId
     * @return
     */
    public String addLoginTicket(int userId){
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(userId);
        Date now =new Date();
        now.setTime(now.getTime()+1000*3600*24);
        loginTicket.setExpired(now);
        //0表示存活滑稽
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        loginTicketDao.addTicketDao(loginTicket);
        return loginTicket.getTicket();
    }
    public User getUserById(int userId){
        return userDao.selectById(userId);
    }
}
