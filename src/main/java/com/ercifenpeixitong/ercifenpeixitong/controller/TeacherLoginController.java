package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.domain.Permission;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Role;
import com.ercifenpeixitong.ercifenpeixitong.domain.User;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TeacherLoginController {
    @Resource
    private TeacherService teacherService;

    //教师登陆
    @RequestMapping("teacherLogin")
    public String teacherLogin(){
        return "/teacher/teacher_login";
    }
    public String teacherLoginHandler(String username,String password){
        return "/teacher/teacherDetil";
    }
}
