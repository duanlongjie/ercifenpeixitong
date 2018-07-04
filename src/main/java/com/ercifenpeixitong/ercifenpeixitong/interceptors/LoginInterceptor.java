package com.ercifenpeixitong.ercifenpeixitong.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("url:"+request.getRequestURL());
        System.out.println("urI:"+request.getRequestURI());
        HttpSession session = request.getSession();
        Object token = session.getAttribute("token");
        Object permissionHrefs = session.getAttribute("permissionHrefs");
        if(token instanceof Integer && token!=null){
//        if(permissionHrefs instanceof ArrayList){
//            if(((ArrayList) permissionHrefs).contains(request.getRequestURL())||((ArrayList) permissionHrefs).contains(request.getRequestURI())){
//                return true;
//            }
//        }
            return true;
        }
        //身份验证失败
        else {
            response.sendRedirect("login");
//            request.getRequestDispatcher("login").forward(request,response);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
