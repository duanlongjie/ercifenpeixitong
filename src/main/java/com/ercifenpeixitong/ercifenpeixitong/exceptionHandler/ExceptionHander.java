package com.ercifenpeixitong.ercifenpeixitong.exceptionHandler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 *  dao service controller 都 将异常抛出去  抛到这里统一管理
 */
@ControllerAdvice
public class ExceptionHander {


    /**
     * 声明 要捕获的异常类
     * ExceptionHandler(value = Exception.class)
     */
    public Object errorHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception e){

        e.printStackTrace();  //打印 错误信息  也可以 将错误信息封装起来
        ModelAndView mv =new ModelAndView();
        mv.addObject("exception",e);
        mv.addObject("url",request.getRequestURL());

        mv.setViewName("error");
        return mv;

    }
}
