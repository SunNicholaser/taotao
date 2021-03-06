package com.taotao.portal;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登陆拦截器
 * Created by ASUS on 2017/11/25.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //从cookie中取token
        //如果没有token跳转到登陆页面
        //娶到token，需要调用sso系统的服务查询用户信息
        TbUser user = userService.getUserByToken(httpServletRequest, httpServletResponse);
        //如果用户session已经过期，跳转到登陆页面
        if(user==null){
            httpServletResponse.sendRedirect(SSO_LOGIN_URL+"?redirectURL="+httpServletRequest.getRequestURL());
            return false;
        }
        //把用户对象放入request中
        httpServletRequest.setAttribute("user",user);
        //如果没有过期，放行

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
