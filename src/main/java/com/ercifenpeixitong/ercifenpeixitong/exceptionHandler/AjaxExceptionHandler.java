package com.ercifenpeixitong.ercifenpeixitong.exceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截ajax 异常请求
 */
@RestControllerAdvice
public class AjaxExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public void errorHandler(HttpServletRequest request , Exception e){
        System.out.println("AjaxExceptionHandler");
        //判断 一个请求 是否是 ajax请求
//        if(request.getHeader("X-Requested-With") != null &&
//                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())){
//            System.out.println();
//        }

        System.out.println(request.getRequestURL());
        e.printStackTrace();

    }
}
