package com.ercifenpeixitong.ercifenpeixitong.controller;

import com.ercifenpeixitong.ercifenpeixitong.domain.Permission;
import com.ercifenpeixitong.ercifenpeixitong.domain.ResultInfo;
import com.ercifenpeixitong.ercifenpeixitong.domain.Role;
import com.ercifenpeixitong.ercifenpeixitong.domain.User;
import com.ercifenpeixitong.ercifenpeixitong.service.UserServiece;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {
    @Resource
    private UserServiece userServiece;
    //后台用户登陆
    @RequestMapping("login")
    public String login(Model model){
        model.addAttribute("error","1");
        return "admin/login";
    }

    @RequestMapping("loginHandler")
    public String loginHandler(String username, String password,
                               HttpSession session,Model model){
        ResultInfo<User> resultInfo = userServiece.findByUserNameAndPassword(username, password);
        User user = resultInfo.getResultObj();
        //用户不存在 重新登陆
        if(user==null){
            model.addAttribute("error","错误的用户名或者密码!");
            return "admin/login";
        }
        System.out.println(user.getId());
        //用户身份合法  可以登陆
        session.setAttribute("token",user.getId());
        return "redirect:home";
    }
    @RequestMapping("home")
    public String home(HttpSession session,Model model){
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
        return "home";
    }


    //教师登陆
    @RequestMapping("teacherLogin")
    public String teacherLogin(){
        return "/teacher/teacher_login";
    }
    public String teacherLoginHandler(String username,String password){
        return "/teacher/teacherDetil";
    }
}
