package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.domain.*;
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
@RequestMapping("admin")
public class TeacherController {
    @Resource
    private UserServiece userServiece;
    @Resource
    private TeacherService teacherService;
    @RequestMapping("teacherHome")
    public String teacherHome(HttpSession session, Model model){
        List<Permission> permissionList  =new ArrayList<>();
        Object token = session.getAttribute("token");
        if(token instanceof Integer){
            ResultInfo<User> resultInfo = userServiece.findById((Integer) token);
            User user = resultInfo.getResultObj();
            if(user!=null){
                List<Role> roles = user.getRoles();
                for(Role role:roles){
                    List<Permission> permissions = role.getPermissions();
                    for(Permission permission:permissions){
                        permissionList.add(permission);
                    }
                }
            }
        }
        for(Permission p:permissionList){
            System.out.println("href:"+p.getHref());
        }
        model.addAttribute("permissionList",permissionList);
        ResultInfo<List<Teacher>> resultInfo = teacherService.getAllTeachers();
        List<Teacher> teachers = resultInfo.getResultObj();
        model.addAttribute("teachers",teachers);
        return "admin/teacherList";
    }
}
