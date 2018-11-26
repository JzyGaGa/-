package com.nowcoder.service;

import com.nowcoder.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nowcoder on 2016/7/10.
 */
@Service
public class WendaService {
    @Autowired
    UserDao userDao;
    public String getMessage(int userId) {
        return "Hello Message:" + String.valueOf(userId);
    }
}
