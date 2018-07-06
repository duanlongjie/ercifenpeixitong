package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.dao.DepartmentDao;
import com.ercifenpeixitong.ercifenpeixitong.domain.*;
import com.ercifenpeixitong.ercifenpeixitong.service.TeacherService;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class TeacherLoginController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private DepartmentDao departmentDao;
    //教师登陆
    @RequestMapping("teacherLogin")
    public String teacherLogin(){
        return "/teacher/teacher_login";
    }
    @RequestMapping("teacherLoginHandler")
    public String teacherLoginHandler(String gongHao,String password,
                                      Model model,HttpSession session){
        Optional<Department> optional = departmentDao.findById(1);
        Department department = optional.get();
        Integer isDel = department.getIsDelete();
        System.out.println(gongHao+"++++"+password);
        ResultInfo<Teacher> resultInfo = teacherService.findByGongHaoAndPassword(gongHao, password);
        Teacher teacher = resultInfo.getResultObj();
        List<Declaration> declarations = teacher.getDeclarations();
        if(teacher!=null){
            model.addAttribute("teacher",teacher);
            model.addAttribute("declarations",declarations);
            model.addAttribute("isDel",isDel);
            session.setAttribute("tToken",teacher.getGongHao());
            return "teacher/teacherDetil";
        }
        return "teacher/teacher_login";
    }

    @RequestMapping("teacherloginOut")
    public String loginOut(){
        return "teacher/teacher_login";
    }

    @RequestMapping("findAll")
    public String findAll(Model model){
        ResultInfo<List<Teacher>> resultInfo = teacherService.getAllTeachers();
        List<Teacher> teachers = resultInfo.getResultObj();
        model.addAttribute("teachers",teachers);
        return "teacher/tlist";
    }
}
