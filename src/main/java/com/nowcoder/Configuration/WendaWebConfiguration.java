package com.nowcoder.Configuration;

import com.nowcoder.Interceptor.LoginInterceptor;
import com.nowcoder.Interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Component
public class WendaWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;
    @Autowired
    LoginInterceptor  LoginInterceptor;
    @Override
    /**
     * 注册 拦截器
     */
    public void addInterceptors(InterceptorRegistry registry) {

       registry.addInterceptor(passportInterceptor);
       registry.addInterceptor(LoginInterceptor).addPathPatterns("/user/*");
    }


}
